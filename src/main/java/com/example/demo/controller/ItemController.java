package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import com.example.demo.utils.VarList;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/travel/item")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ResponseDTO responseDTO;

    // Add new item
    @PostMapping(value = "/addItem")
    public ResponseEntity<ResponseDTO> addNewItemToCart(@RequestBody Item item) {
        if(item == null) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Empty item object");
            responseDTO.setContent(null);
        }
        else {
            try {
                String message = itemService.addNewItem(item);
                if(message != "Item added successfully") {
                    responseDTO.setCode(VarList.RSP_FAIL);
                    responseDTO.setMessage(message);
                    responseDTO.setContent(null);
                }
                else {
                    responseDTO.setCode(VarList.RSP_FAIL);
                    responseDTO.setMessage(message);
                    responseDTO.setContent(null);
                }
            } catch (Exception e) {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage(e.getMessage());
                responseDTO.setContent(null);
            }
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/allItems")
    public ResponseEntity<ResponseDTO> getAllItems() {
        try {
            List<Item> items = itemService.getAllItems();
            if(!items.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing all items");
                responseDTO.setContent(items);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No items found");
                responseDTO.setContent(null);
            }
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return ResponseEntity.ok(responseDTO);
        }
    }

    @GetMapping(value = "/specificItem/{itemId}")
    public ResponseEntity<ResponseDTO> getItemById(@PathVariable long itemId) {
        try {
            Item item = itemService.getItemById(itemId);
            if(item != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Sending item");
                responseDTO.setContent(item);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No item found");
                responseDTO.setContent(null);
            }
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return ResponseEntity.ok(responseDTO);
        }
    }

    @PutMapping(value = "/updateItem/{itemId}")
    public ResponseEntity<ResponseDTO> updateItem(@PathVariable long itemId, @RequestBody Item item) {
        if(itemId <= 0) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Invalid item id");
            responseDTO.setContent(null);
            return ResponseEntity.ok(responseDTO);
        }
        else if(item == null) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Empty item object");
            responseDTO.setContent(null);
            return ResponseEntity.ok(responseDTO);
        }
        else {
            try {
                Item updatedItem = itemService.updateItem(itemId, item);
                if (updatedItem != null) {
                    responseDTO.setCode(VarList.RSP_SUCCESS);
                    responseDTO.setMessage("Item updated successfully");
                    responseDTO.setContent(updatedItem);
                } else {
                    responseDTO.setCode(VarList.RSP_FAIL);
                    responseDTO.setMessage("No item found");
                    responseDTO.setContent(null);
                }
                return ResponseEntity.ok(responseDTO);
            } catch (Exception e) {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage(e.getMessage());
                responseDTO.setContent(null);

            }
        }
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(value = "/deleteItem/{itemId}")
    public ResponseEntity<ResponseDTO> deleteItem(@PathVariable long itemId) {
        if(itemId <= 0) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Invalid item id");
            responseDTO.setContent(null);
            return ResponseEntity.ok(responseDTO);
        } else if (itemService.getItemById(itemId) == null){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("No item found");
            responseDTO.setContent(null);
            return ResponseEntity.ok(responseDTO);

        } else {
            try {
                itemService.deleteItem(itemId);
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Item deleted successfully");
                responseDTO.setContent(null);
                return ResponseEntity.ok(responseDTO);
            } catch (Exception e) {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage(e.getMessage());
                responseDTO.setContent(null);
                return ResponseEntity.ok(responseDTO);
            }
        }
    }


}
