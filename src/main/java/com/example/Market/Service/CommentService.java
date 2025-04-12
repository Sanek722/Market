package com.example.Market.Service;

import com.example.Market.Model.Comment;
import com.example.Market.Model.Product;
import com.example.Market.Repositories.CommentRepository;
import com.example.Market.Repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    public Comment addComment(Long productId, String username, String content) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Comment comment = new Comment();
        comment.setUsername(username);
        comment.setContent(content);
        comment.setDateTime(LocalDateTime.now());
        comment.setProduct(product);

        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, String requesterUsername, boolean isAdmin) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!isAdmin && !comment.getUsername().equals(requesterUsername)) {
            throw new AccessDeniedException("You can't delete someone else's comment");
        }

        commentRepository.delete(comment);
    }

    public Comment updateComment(Long commentId, String newContent, String requesterUsername) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUsername().equals(requesterUsername)) {
            throw new AccessDeniedException("You can only update your own comment");
        }

        comment.setContent(newContent);
        comment.setDateTime(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByProductId(Long productId) {
        return commentRepository.findByProductIdOrderByDateTimeDesc(productId);
    }
}
