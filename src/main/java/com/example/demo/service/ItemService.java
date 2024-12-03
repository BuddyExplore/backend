package com.example.demo.service;

import com.example.demo.dto.item.ItemDTO;
import com.example.demo.dto.item.ItemListDTO;
import com.example.demo.dto.item.ItemRequestDTO;
import com.example.demo.dto.shop.ShopOneDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.Shop;
import com.example.demo.repo.ItemRepository;
import com.example.demo.repo.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;

    public Item saveItem(ItemRequestDTO itemRequestDTO) {
        Shop shop = shopRepository.findById(itemRequestDTO.getShopID())
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        Item item = new Item();
        item.setName(itemRequestDTO.getName());
        item.setDescription(itemRequestDTO.getDescription());
        item.setPrice(itemRequestDTO.getPrice());
        item.setItem_count(itemRequestDTO.getItem_count());
        item.setItem_category(itemRequestDTO.getItem_category());
        item.setIs_available(itemRequestDTO.getIs_available());
        item.setShop(shop);

        return itemRepository.save(item);
    }

    public List<ItemListDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();

        // Convert the list of Item entities to a list of ItemDTOs
        return items.stream()
                .map(item -> {
                    ItemListDTO itemListDTO = new ItemListDTO();
                    itemListDTO.setId(item.getId());
                    itemListDTO.setName(item.getName());
                    itemListDTO.setPrice(item.getPrice());
                    itemListDTO.setItem_category(item.getItem_category());
                    itemListDTO.setIs_available(item.getIs_available());

                    return itemListDTO;
                }).collect(Collectors.toList());
    }

    public ItemDTO getItemById(long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setItem_count(item.getItem_count());
        itemDTO.setItem_category(item.getItem_category());
        itemDTO.setIs_available(item.getIs_available());
        if (item.getShop() != null) {
            Shop shop  = item.getShop();
            ShopOneDTO shopOneDTO = new ShopOneDTO();
            shopOneDTO.setId(shop.getId());
            shopOneDTO.setName(shop.getName());
            shopOneDTO.setAddress(shop.getAddress());
            shopOneDTO.setCity(shop.getCity());
            shopOneDTO.setDescription(shop.getDescription());
            shopOneDTO.setPhone_no(shop.getPhone_no());
            shopOneDTO.setEmail(shop.getEmail());
            shopOneDTO.setCoverImage(shop.getCoverImage());
            shopOneDTO.setRating(shop.getRating());

            itemDTO.setShop(shopOneDTO);
        }

        return itemDTO;
    }

    // Update an Item
    public Item updateItem(Long id, ItemRequestDTO itemRequestDTO) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Shop shop = shopRepository.findById(itemRequestDTO.getShopID())
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        existingItem.setName(itemRequestDTO.getName());
        existingItem.setDescription(itemRequestDTO.getDescription());
        existingItem.setPrice(itemRequestDTO.getPrice());
        existingItem.setItem_count(itemRequestDTO.getItem_count());
        existingItem.setItem_category(itemRequestDTO.getItem_category());
        existingItem.setIs_available(itemRequestDTO.getIs_available());
        existingItem.setShop(shop);

        return itemRepository.save(existingItem);
    }

    // Delete an Item
    public boolean deleteItem(Long itemId) {
        if (itemRepository.existsById(itemId)) {
            itemRepository.deleteById(itemId);
            return true;
        }

        return false;
    }
}

//import com.example.demo.dto.ResponseDTO;
//import com.example.demo.entity.Item;
//import com.example.demo.entity.Shop;
//import com.example.demo.repo.ItemRepository;
//import com.example.demo.repo.ShopRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ItemService {
//    @Autowired
//    private ItemRepository itemRepository;
//
//    @Autowired
//    private ShopRepository shopRepository;
//
//    public String addNewItem(Item item) {
//        if(item.getName() == null || item.getName().isEmpty()) {
//            return "Item name is required";
//        }
//        if(item.getPrice() == 0 || item.getPrice() < 0) {
//            return "Item price is required";
//        }
//        if(item.getItem_count() == 0 || item.getItem_count() < 0) {
//            return "Item quantity is required";
//        }
//        if(item.getItem_category() == null || item.getItem_category().isEmpty()) {
//            return "Item category is required";
//        }
//        if(item.getIs_available() == null || !((item.getIs_available() == false) || (item.getIs_available() == true))) {
//            return "Item availability is required";
//        }
//        if(item.getShop_id() == 0 || item.getShop_id()<0 ){
//            return "Shop id required";
//        }
//        if(item.getDescription() == null || item.getDescription().isEmpty()){
//            return "Description can't  be null";
//        }
//        Shop shop = shopRepository.getShopById(item.getShop_id());
//        if(shop == null){
//            return "Invalid shop id";
//        }
//        itemRepository.save(item);
//        return "Item added successfully";
//
//    }
//
//    public List<Item> getAllItems() {
//        List<Item> items = itemRepository.findAll();
//        return items;
//    }
//
//    public Item getItemById(long itemId) {
//        return itemRepository.getItemById(itemId);
//    }
//
//    public Item updateItem(long itemId, Item item) {
//        Item itemToUpdate = itemRepository.getItemById(itemId);
//        if(itemToUpdate == null) {
//            return null;
//        }
//        if(item.getName() != null || !item.getName().isEmpty()) {
//            itemToUpdate.setName(item.getName());
//        }
//        if(item.getPrice() != 0 || item.getPrice() < 0) {
//            itemToUpdate.setPrice(item.getPrice());
//        }
//        if(item.getItem_count() != 0 || item.getItem_count() < 0) {
//            itemToUpdate.setItem_count(item.getItem_count());
//        }
//        if(item.getItem_category() != null || !item.getItem_category().isEmpty()) {
//            itemToUpdate.setItem_category(item.getItem_category());
//        }
//        if(item.getIs_available() != null || !(item.getIs_available() == false) || (item.getIs_available() == true)) {
//            itemToUpdate.setIs_available(item.getIs_available());
//        }
//        if(item.getShop_id() == 0 || item.getShop_id()<0 ){
//            itemToUpdate.setShop_id(item.getShop_id());
//        }
//        if(item.getDescription() == null || item.getDescription().isEmpty()){
//            itemToUpdate.setDescription(item.getDescription());
//        }
//        Shop shop = shopRepository.getShopById(item.getShop_id());
//        if(shop == null){
//            return null;
//        }
//
//        itemRepository.save(itemToUpdate);
//        return itemToUpdate;
//    }
//
//    public void deleteItem(long itemId) {
//        itemRepository.deleteById(itemId);
//    }
//}
