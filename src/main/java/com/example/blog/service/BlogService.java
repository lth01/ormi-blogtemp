package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.dto.*;
import com.example.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> show() {
        List<Article> articleList =  blogRepository.findAll();
        return articleList;
    }

    public Article showById(Long id) {
         Optional<Article> article = blogRepository.findById(id);
         if(!article.isEmpty()) return article.get();
         //Exception
         return null;
    }

    public DeleteArticleResponse delete(Long id) {
        blogRepository.deleteById(id);
        DeleteArticleResponse msg = new DeleteArticleResponse();
        if(showById(id) != null){
            msg.setResponseMsg("삭제 실패!");
        }else{
            msg.setResponseMsg("삭제 성공");
        }

        return msg;
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found " + id));

        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
