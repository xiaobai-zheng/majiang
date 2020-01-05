package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.dto.CommentDto;
import com.bilibili.majiang.demo.model.Comment;
import com.bilibili.majiang.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @PostMapping("/submitComment")
    public String submitComment(@RequestBody CommentDto commentDto, HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGemCreate(System.currentTimeMillis());
        comment.setGemModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentator(user.getId());
        return null;
    }
}
