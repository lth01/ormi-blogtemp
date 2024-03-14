package com.example.blog.dto;

import com.example.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddArticleResponse {
    private Long id;
    private String title;
    private String content;

    public AddArticleResponse(Article article){
        this.id = article.getId();
        this.content = article.getContent();
        this.title = article.getTitle();
    }
}
