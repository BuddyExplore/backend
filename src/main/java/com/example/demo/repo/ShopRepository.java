package com.example.demo.repo;

import com.example.demo.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findById(Long id);

    Shop getShopById(long id);

    @Query(value = "SELECT * FROM shops s WHERE s.shop_owner_id = :shopOwnerID", nativeQuery = true)
    List<Shop> findShopsByShopOwnerID(@Param("shopOwnerID") Long shopOwnerID);
}
