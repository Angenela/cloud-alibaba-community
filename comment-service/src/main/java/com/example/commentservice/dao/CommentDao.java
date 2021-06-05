package com.example.commentservice.dao;

import com.example.commonapi.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface CommentDao {

    int addComment(Comment comment);
}
