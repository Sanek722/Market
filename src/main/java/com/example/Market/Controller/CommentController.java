package com.example.Market.Controller;

import com.example.Market.DTO.CommentDTO.CommentRequest;
import com.example.Market.Model.Comment;
import com.example.Market.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{productId}")
    public ResponseEntity<Comment> addComment(@RequestBody CommentRequest commentRequest,
                                              @RequestHeader("X-User") String username) {
        Comment saved = commentService.addComment(commentRequest.getProductId(), username, commentRequest.getContent());
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,
                                              @RequestHeader("X-User") String username,
                                              @RequestHeader("X-Role") String role) {
        boolean isAdmin = role.equals("Admin");
        commentService.deleteComment(id, username, isAdmin);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id,
                                                 @RequestBody String newContent,
                                                 @RequestHeader("X-User") String username) {
        Comment updated = commentService.updateComment(id, newContent, username);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/product/{productId}")
    public List<Comment> getCommentsForProduct(@PathVariable Long productId) {
        return commentService.getCommentsByProductId(productId);
    }
}

