package com.example.blog.controller;

import com.example.blog.domain.Article;
import com.example.blog.domain.Comment;
import com.example.blog.dto.CommentsResponse;
import com.example.blog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("/comments/{articleId}")
    public ResponseEntity<String> addComment(@PathVariable Long articleId, @RequestBody Comment addRequest){
        Comment comment =  commentService.save(articleId, addRequest);
        String returnStr = comment == null ? "저장 실패" : comment.getBody();


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(returnStr);
    }

    @GetMapping("/comments/{articleId}")
    public ResponseEntity<CommentsResponse> getComment(@PathVariable Long articleId){
        CommentsResponse response =  commentService.getComments(articleId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }


}
