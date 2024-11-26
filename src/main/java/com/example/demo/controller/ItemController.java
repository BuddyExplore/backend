package com.example.demo.controller;


import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.Shop;
import com.example.demo.repo.ShopRepository;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/travel/item")
@RequiredArgsConstructor
public class ItemController {

    private final ShopRepository shopRepository;
    private final ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {
        try {
            // Get the Shop object from the Shop ID
            Shop shop = shopRepository.findById(itemDTO.getShop_id())
                    .orElseThrow(() -> new RuntimeException("Shop not found"));

            // Create a new Item and map it to the DTO
            Item newItem = new Item();
            newItem.setName(itemDTO.getName());
            newItem.setPrice(itemDTO.getPrice());
            newItem.setDescription(itemDTO.getDescription());
            newItem.setPrice(itemDTO.getPrice());
            newItem.setItem_count(itemDTO.getItem_count());
            newItem.setItem_category(itemDTO.getItem_category());
            newItem.setIs_available(itemDTO.getIs_available());
            newItem.setShop(shop);

            // Save the item
            Item savedItem = itemService.saveItem(newItem);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating shop: " + e.getMessage());
        }
    }

    @GetMapping("/allItems")
    public ResponseEntity<?> getAllItems() {
        try {
            return ResponseEntity.ok(itemService.getAllItems());
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching items: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable long id) {
        try {
            ItemDTO item = itemService.getItemById(id);
            if(item != null) {
                return ResponseEntity.ok(item);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
            }
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching item: " + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateItem(@PathVariable long id, @RequestBody ItemDTO itemDTO) {
        try {
            Item updatedItem = new Item();
            updatedItem.setName(itemDTO.getName());
            updatedItem.setPrice(itemDTO.getPrice());
            updatedItem.setDescription(itemDTO.getDescription());
            updatedItem.setPrice(itemDTO.getPrice());
            updatedItem.setItem_count(itemDTO.getItem_count());
            updatedItem.setItem_category(itemDTO.getItem_category());
            updatedItem.setIs_available(itemDTO.getIs_available());
            Shop shop = shopRepository.findById(itemDTO.getShop_id())
                    .orElseThrow(() -> new RuntimeException("Shop not found"));
            updatedItem.setShop(shop);

            Item updatedItemEntity = itemService.updateItem(id, updatedItem);

            return ResponseEntity.ok("Updated Successfully !");
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating shop: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        try {
            boolean deleted = itemService.deleteItem(id);
            if (deleted) {
                return ResponseEntity.ok("Item deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
            }
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting shop: " + e.getMessage());
        }
    }

}


//import com.example.demo.dto.ResponseDTO;
//import com.example.demo.entity.Item;
//import com.example.demo.service.ItemService;
//import com.example.demo.utils.VarList;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "api/travel/item")
//@AllArgsConstructor
//public class ItemController {
//    private final ItemService itemService;
//    private final ResponseDTO responseDTO;
//
//    // Add new item
//    @PostMapping(value = "/addItem")
//    public ResponseEntity<ResponseDTO> addNewItemToCart(@RequestBody Item item) {
//        System.out.println("Creating item");
//        if(item == null) {
//            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
//            responseDTO.setMessage("Empty item object");
//            responseDTO.setContent(null);
//        }
//        else {
//            try {
//                String message = itemService.addNewItem(item);
//                if(message != "Item added successfully") {
//                    responseDTO.setCode(VarList.RSP_FAIL);
//                    responseDTO.setMessage(message);
//                    responseDTO.setContent(null);
//                }
//                else {
//                    responseDTO.setCode(VarList.RSP_FAIL);
//                    responseDTO.setMessage(message);
//                    responseDTO.setContent(null);
//                }
//            } catch (Exception e) {
//                responseDTO.setCode(VarList.RSP_ERROR);
//                responseDTO.setMessage(e.getMessage());
//                responseDTO.setContent(null);
//            }
//        }
//        System.out.println(responseDTO.getMessage());
//        return ResponseEntity.ok(responseDTO);
//    }
//
//    @GetMapping(value = "/allItems")
//    public ResponseEntity<ResponseDTO> getAllItems() {
//        try {
//            List<Item> items = itemService.getAllItems();
//            if(!items.isEmpty()) {
//                responseDTO.setCode(VarList.RSP_SUCCESS);
//                responseDTO.setMessage("Listing all items");
//                responseDTO.setContent(items);
//            } else {
//                responseDTO.setCode(VarList.RSP_FAIL);
//                responseDTO.setMessage("No items found");
//                responseDTO.setContent(null);
//            }
//            return ResponseEntity.ok(responseDTO);
//        } catch (Exception e) {
//            responseDTO.setCode(VarList.RSP_ERROR);
//            responseDTO.setMessage(e.getMessage());
//            responseDTO.setContent(null);
//            return ResponseEntity.ok(responseDTO);
//        }
//    }
//
//    @GetMapping(value = "/specificItem/{itemId}")
//    public ResponseEntity<ResponseDTO> getItemById(@PathVariable long itemId) {
//        try {
//            Item item = itemService.getItemById(itemId);
//            if(item != null) {
//                responseDTO.setCode(VarList.RSP_SUCCESS);
//                responseDTO.setMessage("Sending item");
//                responseDTO.setContent(item);
//            } else {
//                responseDTO.setCode(VarList.RSP_FAIL);
//                responseDTO.setMessage("No item found");
//                responseDTO.setContent(null);
//            }
//            return ResponseEntity.ok(responseDTO);
//        } catch (Exception e) {
//            responseDTO.setCode(VarList.RSP_ERROR);
//            responseDTO.setMessage(e.getMessage());
//            responseDTO.setContent(null);
//            return ResponseEntity.ok(responseDTO);
//        }
//    }
//
//    @PutMapping(value = "/updateItem/{itemId}")
//    public ResponseEntity<ResponseDTO> updateItem(@PathVariable long itemId, @RequestBody Item item) {
//        if(itemId <= 0) {
//            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
//            responseDTO.setMessage("Invalid item id");
//            responseDTO.setContent(null);
//            return ResponseEntity.ok(responseDTO);
//        }
//        else if(item == null) {
//            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
//            responseDTO.setMessage("Empty item object");
//            responseDTO.setContent(null);
//            return ResponseEntity.ok(responseDTO);
//        }
//        else {
//            try {
//                Item updatedItem = itemService.updateItem(itemId, item);
//                if (updatedItem != null) {
//                    responseDTO.setCode(VarList.RSP_SUCCESS);
//                    responseDTO.setMessage("Item updated successfully");
//                    responseDTO.setContent(updatedItem);
//                } else {
//                    responseDTO.setCode(VarList.RSP_FAIL);
//                    responseDTO.setMessage("No item found");
//                    responseDTO.setContent(null);
//                }
//                return ResponseEntity.ok(responseDTO);
//            } catch (Exception e) {
//                responseDTO.setCode(VarList.RSP_ERROR);
//                responseDTO.setMessage(e.getMessage());
//                responseDTO.setContent(null);
//
//            }
//        }
//        return ResponseEntity.ok(responseDTO);
//    }
//
//    @DeleteMapping(value = "/deleteItem/{itemId}")
//    public ResponseEntity<ResponseDTO> deleteItem(@PathVariable long itemId) {
//        if(itemId <= 0) {
//            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
//            responseDTO.setMessage("Invalid item id");
//            responseDTO.setContent(null);
//            return ResponseEntity.ok(responseDTO);
//        } else if (itemService.getItemById(itemId) == null){
//            responseDTO.setCode(VarList.RSP_FAIL);
//            responseDTO.setMessage("No item found");
//            responseDTO.setContent(null);
//            return ResponseEntity.ok(responseDTO);
//
//        } else {
//            try {
//                itemService.deleteItem(itemId);
//                responseDTO.setCode(VarList.RSP_SUCCESS);
//                responseDTO.setMessage("Item deleted successfully");
//                responseDTO.setContent(null);
//                return ResponseEntity.ok(responseDTO);
//            } catch (Exception e) {
//                responseDTO.setCode(VarList.RSP_ERROR);
//                responseDTO.setMessage(e.getMessage());
//                responseDTO.setContent(null);
//                return ResponseEntity.ok(responseDTO);
//            }
//        }
//    }
//
//
//}
