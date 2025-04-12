package com.example.Market.Service;

import com.example.Market.Model.Comment;
import com.example.Market.Repositories.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void add(Comment comment) {
        commentRepository.saveAndFlush(comment);
    }
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    public void updateComment(Comment comment)
    {
        commentRepository.saveAndFlush(comment);
    }
    public Comment getComment(Long id)
    {
        return commentRepository.getById(id);
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }
}
