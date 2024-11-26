package com.example.demo.repo;

import com.example.demo.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findById(Long id);

    Shop getShopById(long id);
}
