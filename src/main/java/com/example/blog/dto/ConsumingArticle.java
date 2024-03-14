package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumingArticle {
    private Long id;
    private Long usrId;
    private String title;
    private String body;
}
