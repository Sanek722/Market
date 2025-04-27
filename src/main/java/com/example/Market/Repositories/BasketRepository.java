package com.example.Market.Repositories;

import com.example.Market.Model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByUsername(String username);

    Optional<Basket> findByUsernameAndProductId(String username, Long productId);
}
