package com.example.blog.controller;

import com.example.blog.domain.Article;
import com.example.blog.dto.*;
import com.example.blog.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BlogController {
    private BlogService blogService;

    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> showArticle(){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.show());
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<Article> showArticle(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.showById(id));
    }

    // HTTP요청이 'POST /api/articles' 경로일 때 해당 메소드로 매핑
    @PostMapping("/api/articles")
    public ResponseEntity<AddArticleResponse> addArticle(@RequestBody AddArticleRequest request) { // RequestBody로 요청 본문 값 매핑
        Article article = blogService.save(request);
        AddArticleResponse savedResponse = article.toResponse();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedResponse);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<DeleteArticleResponse> removeArticle(@PathVariable Long id){
        DeleteArticleResponse response = blogService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        Article updatedArticle =  blogService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(updatedArticle);
    }
}
