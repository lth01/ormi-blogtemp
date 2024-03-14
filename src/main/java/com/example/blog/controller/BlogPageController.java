package com.example.blog.controller;

import com.example.blog.domain.Article;
import com.example.blog.dto.ArticleViewResponse;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {
    private BlogService blogService;

    @Autowired
    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleViewResponse> articles = blogService.show().stream()
                .map(ArticleViewResponse::new)
                .toList();

        model.addAttribute("articles", articles);

        //articleList.html 뷰 조회
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String showArticle(@PathVariable Long id, Model model){
        Article article = blogService.showById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null){
            model.addAttribute("article", new ArticleViewResponse());
        }else{
            Article article = blogService.showById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }

}
