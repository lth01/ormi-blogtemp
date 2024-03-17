package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.domain.Comment;
import com.example.blog.dto.ArticleResponse;
import com.example.blog.dto.CommentsResponse;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public Comment save(Long article_id, Comment addCommentRequest){
        Optional<Article> article = blogRepository.findById(article_id);
        if(article.isEmpty()) return null;
        addCommentRequest.setArticle(article.get());
        return commentRepository.save(addCommentRequest);
    }

    public CommentsResponse getComments(Long article_id) {
        Optional<Article> article = blogRepository.findById(article_id);
        CommentsResponse commentsResponse = new CommentsResponse();
        List<Comment> commentList = null;
        if(article.isPresent()){
                commentsResponse.setArticle(article.get());
                commentList = commentRepository.findByArticle(article.get());
        }
        commentsResponse.setCommentList(commentList);
        return commentsResponse;
    }
}
