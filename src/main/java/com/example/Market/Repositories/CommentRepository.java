package com.example.Market.Repositories;

import com.example.Market.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{
    List<Comment> findByProductIdOrderByDateTimeDesc(Long productId);
}
