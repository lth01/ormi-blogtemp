package com.example.blog.dto;

import com.example.blog.domain.Article;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }

    public ArticleViewResponse(){}
}
