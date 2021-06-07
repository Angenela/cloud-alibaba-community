package com.example.client.controller.topic;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.commonapi.dto.ImgUploadDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

@RestController
public class TopicServiceController {
    private static final Log logger = LogFactory.getLog(TopicServiceController.class);
    private static final String EDITOR_IMG_NAME = "editormd-image-file";

    @Value("${endpoint}")
    private String endpoint;
    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessKeySecret}")
    private String accessKeySecret;
    @Value("${bucketName}")
    private String bucketName;
    @Value("${imgUploadPath}")
    private String imgUploadPath;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/image/upload")
    public String imageUpload(HttpServletRequest request) throws JsonProcessingException {
        ImgUploadDTO imgUploadDTO = new ImgUploadDTO();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile(EDITOR_IMG_NAME);
        // 创建文件名
        String filename = file.getOriginalFilename();

        logger.info(
                "\nSTART POST /image/upload" +
                        "\nfilename: " + filename
        );

        InputStream inputStream;
        OSS ossClient = null;
        String path = imgUploadPath + filename;
        try {
            inputStream = file.getInputStream();
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build("https://" + endpoint, accessKeyId, accessKeySecret);

            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, path, inputStream);
        } catch (Exception e) {
            logger.error("\nERROR POST /image/upload" + "\nfilename: " + filename, e);
            imgUploadDTO.setSuccess(0);
            imgUploadDTO.setMessage("上传错误！请稍后重试");
            return objectMapper.writeValueAsString(imgUploadDTO);
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }

        String url = "https://" + bucketName + "." + endpoint + "/" + path;

        imgUploadDTO.setSuccess(1);
        imgUploadDTO.setMessage("上传成功");
        imgUploadDTO.setUrl(url);

        String json = objectMapper.writeValueAsString(imgUploadDTO);

        logger.info(
                "\nEND POST /image/upload" +
                        "\n返回的JSON: " + json
        );

        return json;
    }
}
