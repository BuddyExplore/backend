package com.example.demo.service;

import com.example.demo.dto.item.ItemListDTO;
import com.example.demo.dto.shop.ShopDTO;
import com.example.demo.dto.shop.ShopListDTO;
import com.example.demo.dto.shop.ShopOneDTO;
import com.example.demo.entity.Shop;
import com.example.demo.entity.User;
import com.example.demo.repo.AuthUserRepository;
import com.example.demo.repo.ItemRepository;
import com.example.demo.repo.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;

    public List<ShopListDTO> getAllShops() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopListDTO> shopListDTOS = new ArrayList<>();

        for(Shop shop: shops) {
            ShopListDTO shopListDTO = convertToDTO(shop);
            shopListDTOS.add(shopListDTO);
        }

        return shopListDTOS;
    }

    public List<ShopListDTO> getShopsByShopOwnerID(Long shopOwnerID) {
        List<Shop> shops = shopRepository.findShopsByShopOwnerID(shopOwnerID);
        List<ShopListDTO> shopListDTOS = new ArrayList<>();

        for(Shop shop: shops) {
            ShopListDTO shopListDTO =  convertToDTO(shop);
            shopListDTOS.add(shopListDTO);
        }

        return shopListDTOS;
    }

    private ShopListDTO convertToDTO(Shop shop) {
        return ShopListDTO.builder()
                .id(shop.getId())
                .name(shop.getName())
                .city(shop.getCity())
                .coverImage(shop.getCoverImage())
                .itemCount(itemRepository.getItemCountByShopID(shop.getId()))
                .build();
    }

    public ShopDTO getShopById(long id) {

         Shop shop = shopRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Item not found"));

         ShopDTO shopDTO = new ShopDTO();
         shopDTO.setId(shop.getId());
         shopDTO.setName(shop.getName());
         shopDTO.setAddress(shop.getAddress());
         shopDTO.setCity(shop.getCity());
         shopDTO.setDescription(shop.getDescription());
         shopDTO.setPhone_no(shop.getPhone_no());
         shopDTO.setEmail(shop.getEmail());
         shopDTO.setCoverImage(shop.getCoverImage());
         shopDTO.setRating(shop.getRating());

         List<ItemListDTO> itemListDTOs = itemRepository.findAllByShopID(shop.getId()).stream().map(item -> {
             ItemListDTO itemListDTO = new ItemListDTO();
             itemListDTO.setId(item.getId());
             itemListDTO.setName(item.getName());
             itemListDTO.setPrice(item.getPrice());
             itemListDTO.setCover_image(item.getCover_image());
             itemListDTO.setItem_category(item.getItem_category());
             itemListDTO.setIs_available(item.getIs_available());
             return itemListDTO;
         }).collect(Collectors.toList());

        shopDTO.setItems(itemListDTOs);
        return shopDTO;
    }

    public Shop addNewShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public Shop registerShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public Shop updateShop(long id, Shop shop) {
        Optional<Shop> optionalShop = shopRepository.findById(id);

        if (optionalShop.isPresent()) {
            Shop shopDetails = optionalShop.get();
            shopDetails.setName(shop.getName());

            return shopRepository.save(shopDetails);
        }

        return null;
    }

    public boolean deleteShop(long id) {
        if (shopRepository.existsById(id)) {
            shopRepository.deleteById(id);
            return true;
        }
        return false;
    }
}



















//import com.example.demo.entity.Shop;
//import com.example.demo.repo.ShopRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ShopService {
//    @Autowired
//    private ShopRepository shopRepository;
//
//    public String addNewShop(Shop shop) {
//        if(shop.getName() == null || shop.getName().isEmpty()) {
//            return "Shop name is required";
//        }
//        if(shop.getAddress() == null || shop.getAddress().isEmpty()) {
//            return "Shop address is required";
//        }
//        shop.setRating(0);
//        shopRepository.save(shop);
//        return "Shop added successfully";
//    }
//
//    public List<Shop> getAllShops() {
//        List<Shop> shops = shopRepository.findAll();
//        return shops;
//    }
//
//    public Shop getShopById(Long shopId) {
//        return shopRepository.getShopById(shopId);
//    }
//
//    public String updateShop(Long shopId, Shop shop) {
//        Shop shopToUpdate = shopRepository.getShopById(shopId);
//        if(shopToUpdate == null) {
//            return "Shop not found";
//        }
//        if(shop.getName() != null || !shop.getName().isEmpty()) {
//            shopToUpdate.setName(shop.getName());
//        }
//        if(shop.getAddress() != null || !shop.getAddress().isEmpty()) {
//            shopToUpdate.setAddress(shop.getAddress());
//        }
//        if(shop.getRating() != 0 || shop.getRating() < 0) {
//            shopToUpdate.setRating(shop.getRating());
//        }
//        shopRepository.save(shopToUpdate);
//        return "Shop updated successfully";
//    }
//
//    public void deleteShop(Long shopId) {
//        shopRepository.deleteById(shopId);
//    }
//}
