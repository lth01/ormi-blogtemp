package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdatArticleRequest {
    private String title;
    private String content;
}
