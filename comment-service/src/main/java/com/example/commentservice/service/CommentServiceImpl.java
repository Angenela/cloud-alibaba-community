package com.example.commentservice.service;

import com.example.commentservice.dao.CommentDao;
import com.example.commonapi.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService{



    @Autowired
    private CommentDao commentDao;
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }



    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }
}
