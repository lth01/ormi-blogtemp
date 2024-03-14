package com.example.blog.controller;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.dto.AddArticleResponse;
import com.example.blog.dto.ConsumingArticle;
import com.example.blog.external.ExampleAPIClient;
import com.example.blog.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {
    private final ExampleAPIClient client;
    private final BlogService blogService;

    @Autowired
    public TestController(ExampleAPIClient client, BlogService blogService) {
        this.client = client;
        this.blogService = blogService;
    }

    @GetMapping("/api/consuming")
    public void consumeDatas() throws ParseException, JsonProcessingException {
        String returnStr = client.fetchDataFromApi();

        ObjectMapper om = JsonMapper.builder()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build();
        ConsumingArticle[] ConsumingArticles = om.readValue(returnStr, ConsumingArticle[].class);
        AddArticleRequest[] addArticleRequests = Arrays.stream(ConsumingArticles).map(article -> { return new AddArticleRequest(article.getTitle(), article.getBody());}).toArray(AddArticleRequest[]::new);
        List<Article> list = new ArrayList<>();
        System.out.println(Arrays.toString(addArticleRequests));


        for(AddArticleRequest request : addArticleRequests){
            Article article = blogService.save(request);
            if(article != null){
                list.add(article);
            }
        }

        list.stream().forEach(System.out::println);

    }

}
