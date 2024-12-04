package com.example.demo.repo;

import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findById(Long id);

    Item getItemById(long id);

    @Query(value = "SELECT * FROM items WHERE shop_id = :shopID", nativeQuery = true)
    List<Item> findAllByShopID(@Param("shopID") Long shopID);

    @Query(value = "SELECT COUNT(id) FROM items WHERE shop_id = :shopID", nativeQuery = true)
    Integer getItemCountByShopID(@Param("shopID") long id);
}
