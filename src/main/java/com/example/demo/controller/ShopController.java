package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.Shop;
import com.example.demo.utils.VarList;
import com.example.demo.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/travel/shop")
@AllArgsConstructor
public class ShopController {
    private final ShopService shopService;
    private final ResponseDTO responseDTO;

    // Add new shop
    @PostMapping(value = "/addShop")
    public ResponseEntity<ResponseDTO> addNewShop(@RequestBody Shop shop) {
        if(shop == null) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Empty shop object");
            responseDTO.setContent(null);
        }
        else {
            try {
                String message = shopService.addNewShop(shop);
                if(message != "Shop added successfully") {
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

    @GetMapping(value = "/allShops")
    public ResponseEntity<ResponseDTO> getAllShops() {
        try {
            List<Shop> shops = shopService.getAllShops();
            if(!shops.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing all shops");
                responseDTO.setContent(shops);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No shops found");
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/specificshopbyid/{shopId}")
    public ResponseEntity<ResponseDTO> getShopById(@PathVariable("shopId") Long shopId) {
        if(shopId == null) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Empty shop id");
            responseDTO.setContent(null);
        }
        else {
            try {
                Shop shop = shopService.getShopById(shopId);
                if(shop != null) {
                    responseDTO.setCode(VarList.RSP_SUCCESS);
                    responseDTO.setMessage("Shop found");
                    responseDTO.setContent(shop);
                } else {
                    responseDTO.setCode(VarList.RSP_FAIL);
                    responseDTO.setMessage("No shop found");
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

    @PutMapping(value = "/updateShop/{shopId}")
    public ResponseEntity<ResponseDTO> updateShop(@PathVariable Long shopId, @RequestBody Shop shop) {
        if(shopId == null) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Empty shop id");
            responseDTO.setContent(null);
        }
        else if(shop == null) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Empty shop object");
            responseDTO.setContent(null);
        }
        else {
            try {
                String message = shopService.updateShop(shopId, shop);
                if(message != "Shop updated successfully") {
                    responseDTO.setCode(VarList.RSP_FAIL);
                    responseDTO.setMessage(message);
                    responseDTO.setContent(null);
                }
                else {
                    responseDTO.setCode(VarList.RSP_SUCCESS);
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

    @DeleteMapping(value = "/deleteShop/{shopId}")
    public ResponseEntity<ResponseDTO> deleteShop(@PathVariable Long shopId) {
        if(shopId == null) {
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Empty shop id");
            responseDTO.setContent(null);
        }
        else {
            try {
                shopService.deleteShop(shopId);
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Shop deleted successfully");
                responseDTO.setContent(null);
            } catch (Exception e) {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage(e.getMessage());
                responseDTO.setContent(null);
            }
        }
        return ResponseEntity.ok(responseDTO);
    }
}
