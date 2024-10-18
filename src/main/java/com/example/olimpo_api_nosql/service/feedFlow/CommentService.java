package com.example.olimpo_api_nosql.service.feedFlow;

import com.example.olimpo_api_nosql.exception.ExceptionThrower;
import com.example.olimpo_api_nosql.model.Comment;
import com.example.olimpo_api_nosql.repository.feedFlow.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment create(Comment comment) {
        return commentRepository.create(comment);
    }

    public List<Comment> getAllOfPublication(String publicationId) {
        List<Comment> comments = commentRepository.getAllOfPublication(publicationId);
        if (comments.isEmpty()) {
            ExceptionThrower.throwNotFoundException("Comments not found");
        }else {
            return comments;
        }
        return null;
    }
}
