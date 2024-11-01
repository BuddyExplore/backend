package com.example.demo.service;

import com.example.demo.entity.Shop;
import com.example.demo.repo.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public String addNewShop(Shop shop) {
        if(shop.getName() == null || shop.getName().isEmpty()) {
            return "Shop name is required";
        }
        if(shop.getAddress() == null || shop.getAddress().isEmpty()) {
            return "Shop address is required";
        }
        shop.setRating(0);
        shopRepository.save(shop);
        return "Shop added successfully";
    }

    public List<Shop> getAllShops() {
        List<Shop> shops = shopRepository.findAll();
        return shops;
    }

    public Shop getShopById(Long shopId) {
        return shopRepository.getShopById(shopId);
    }

    public String updateShop(Long shopId, Shop shop) {
        Shop shopToUpdate = shopRepository.getShopById(shopId);
        if(shopToUpdate == null) {
            return "Shop not found";
        }
        if(shop.getName() != null || !shop.getName().isEmpty()) {
            shopToUpdate.setName(shop.getName());
        }
        if(shop.getAddress() != null || !shop.getAddress().isEmpty()) {
            shopToUpdate.setAddress(shop.getAddress());
        }
        if(shop.getRating() != 0 || shop.getRating() < 0) {
            shopToUpdate.setRating(shop.getRating());
        }
        shopRepository.save(shopToUpdate);
        return "Shop updated successfully";
    }

    public void deleteShop(Long shopId) {
        shopRepository.deleteById(shopId);
    }
}
