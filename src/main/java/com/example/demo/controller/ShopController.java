package com.example.demo.controller;

import com.example.demo.dto.shop.ShopDTO;
import com.example.demo.dto.shop.ShopListDTO;
import com.example.demo.dto.shop.ShopOneDTO;
import com.example.demo.entity.Shop;
import com.example.demo.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(path = "api/travel/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/allShops")
    public ResponseEntity<?> getAllShops() {
        try {
            return ResponseEntity.ok(shopService.getAllShops());
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching products: " + e.getMessage());
        }
    }

    @GetMapping("/allShopsByOwnerID/{shopOwnerID}")
    public ResponseEntity<?> getShopsByShopOwnerEmail(@PathVariable String shopOwnerID) {
        try {
            Long ownerID = Long.parseLong(shopOwnerID);
            List<ShopListDTO> shops = shopService.getShopsByShopOwnerID(ownerID);
            return ResponseEntity.ok(shops);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching products: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShopById(@PathVariable long id) {
        try {
            ShopDTO shopDTO = shopService.getShopById(id);
            if (shopDTO != null) {
                return ResponseEntity.ok(shopDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found");
            }
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching product: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createShop(@RequestBody Shop shop) {
        try {
            Shop saveShop = shopService.addNewShop(shop);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveShop);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating shop: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerShop(
            @RequestPart("name") String name,
            @RequestPart("address") String address,
            @RequestPart("city") String city,
            @RequestPart("description") String description,
            @RequestPart("phoneNo") String phoneNo,
            @RequestPart("email") String email,
            @RequestPart("shopOwnerID") String shopOwnerID,
            @RequestPart("coverImage") MultipartFile coverImage,
            @RequestPart("businessCertificate") MultipartFile businessCertificate) {

        if (coverImage.isEmpty() || businessCertificate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            // Define a base directory for uploads
            String baseDir = "uploads/shop";
            String baseDirBC = "uploads/shop/businessCertificate";

            // Ensure the upload directory exists
            Path uploadPath = Paths.get(baseDir);
            Path uploadPathBC = Paths.get(baseDirBC);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            if(!Files.exists(uploadPathBC)) {
                Files.createDirectories(uploadPathBC);
            }

            // Generate a unique coverImage name to avoid conflicts
            String coverImageName = coverImage.getOriginalFilename();
            String uniqueCoverImageName = System.currentTimeMillis() + "_" + coverImageName;

            String bcName = businessCertificate.getOriginalFilename();
            String uniqueBcName = System.currentTimeMillis() + "_" + bcName;

            // Define the full coverImage path
            Path coverImagePath = uploadPath.resolve(uniqueCoverImageName);
            Path businessCertificatePath = uploadPathBC.resolve(uniqueBcName);

            // Save the coverImage
            Files.copy(coverImage.getInputStream(), coverImagePath);
            Files.copy(businessCertificate.getInputStream(), businessCertificatePath);
            Shop shop = new Shop();
            shop.setName(name);
            shop.setAddress(address);
            shop.setCity(city);
            shop.setDescription(description);
            shop.setPhone_no(phoneNo);
            shop.setEmail(email);
            shop.setShop_owner_id(Long.parseLong(shopOwnerID));
            shop.setCoverImage(coverImagePath.toString().replace("\\", "/"));
            shop.setBusinessCertificate(businessCertificatePath.toString().replace("\\", "/"));

            Shop saveShop = shopService.registerShop(shop);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveShop);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process shop creation");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateShop(@PathVariable long id, @RequestBody Shop shop) {
        try {
            Shop updatedShop = shopService.updateShop(id, shop);
            if(updatedShop != null) {
                return ResponseEntity.ok(updatedShop);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found");
            }
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating shop: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeShop(@PathVariable long id) {
        try {
            boolean deleted = shopService.deleteShop(id);
            if (deleted) {
                return ResponseEntity.ok("Shop deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found");
            }
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting shop: " + e.getMessage());
        }
    }




}

//import com.example.demo.dto.ResponseDTO;
//import com.example.demo.entity.Shop;
//import com.example.demo.utils.VarList;
//import com.example.demo.service.ShopService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "api/travel/shop")
//@AllArgsConstructor
//public class ShopController {
//    private final ShopService shopService;
//    private final ResponseDTO responseDTO;
//
//    // Add new shop
//    @PostMapping(value = "/addShop")
//    public ResponseEntity<ResponseDTO> addNewShop(@RequestBody Shop shop) {
//        if(shop == null) {
//            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
//            responseDTO.setMessage("Empty shop object");
//            responseDTO.setContent(null);
//        }
//        else {
//            try {
//                String message = shopService.addNewShop(shop);
//                if(message != "Shop added successfully") {
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
//        return ResponseEntity.ok(responseDTO);
//    }
//
//    @GetMapping(value = "/allShops")
//    public ResponseEntity<ResponseDTO> getAllShops() {
//        try {
//            List<Shop> shops = shopService.getAllShops();
//            if(!shops.isEmpty()) {
//                responseDTO.setCode(VarList.RSP_SUCCESS);
//                responseDTO.setMessage("Listing all shops");
//                responseDTO.setContent(shops);
//            } else {
//                responseDTO.setCode(VarList.RSP_FAIL);
//                responseDTO.setMessage("No shops found");
//            }
//        } catch (Exception e) {
//            responseDTO.setCode(VarList.RSP_ERROR);
//            responseDTO.setMessage(e.getMessage());
//            responseDTO.setContent(null);
//        }
//        return ResponseEntity.ok(responseDTO);
//    }
//
//    @GetMapping(value = "/specificshopbyid/{shopId}")
//    public ResponseEntity<ResponseDTO> getShopById(@PathVariable("shopId") Long shopId) {
//        if(shopId == null) {
//            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
//            responseDTO.setMessage("Empty shop id");
//            responseDTO.setContent(null);
//        }
//        else {
//            try {
//                Shop shop = shopService.getShopById(shopId);
//                if(shop != null) {
//                    responseDTO.setCode(VarList.RSP_SUCCESS);
//                    responseDTO.setMessage("Shop found");
//                    responseDTO.setContent(shop);
//                } else {
//                    responseDTO.setCode(VarList.RSP_FAIL);
//                    responseDTO.setMessage("No shop found");
//                    responseDTO.setContent(null);
//                }
//            } catch (Exception e) {
//                responseDTO.setCode(VarList.RSP_ERROR);
//                responseDTO.setMessage(e.getMessage());
//                responseDTO.setContent(null);
//            }
//        }
//        return ResponseEntity.ok(responseDTO);
//    }
//
//    @PutMapping(value = "/updateShop/{shopId}")
//    public ResponseEntity<ResponseDTO> updateShop(@PathVariable Long shopId, @RequestBody Shop shop) {
//        if(shopId == null) {
//            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
//            responseDTO.setMessage("Empty shop id");
//            responseDTO.setContent(null);
//        }
//        else if(shop == null) {
//            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
//            responseDTO.setMessage("Empty shop object");
//            responseDTO.setContent(null);
//        }
//        else {
//            try {
//                String message = shopService.updateShop(shopId, shop);
//                if(message != "Shop updated successfully") {
//                    responseDTO.setCode(VarList.RSP_FAIL);
//                    responseDTO.setMessage(message);
//                    responseDTO.setContent(null);
//                }
//                else {
//                    responseDTO.setCode(VarList.RSP_SUCCESS);
//                    responseDTO.setMessage(message);
//                    responseDTO.setContent(null);
//                }
//            } catch (Exception e) {
//                responseDTO.setCode(VarList.RSP_ERROR);
//                responseDTO.setMessage(e.getMessage());
//                responseDTO.setContent(null);
//            }
//        }
//        return ResponseEntity.ok(responseDTO);
//    }
//
//    @DeleteMapping(value = "/deleteShop/{shopId}")
//    public ResponseEntity<ResponseDTO> deleteShop(@PathVariable Long shopId) {
//        if(shopId == null) {
//            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
//            responseDTO.setMessage("Empty shop id");
//            responseDTO.setContent(null);
//        }
//        else {
//            try {
//                shopService.deleteShop(shopId);
//                responseDTO.setCode(VarList.RSP_SUCCESS);
//                responseDTO.setMessage("Shop deleted successfully");
//                responseDTO.setContent(null);
//            } catch (Exception e) {
//                responseDTO.setCode(VarList.RSP_ERROR);
//                responseDTO.setMessage(e.getMessage());
//                responseDTO.setContent(null);
//            }
//        }
//        return ResponseEntity.ok(responseDTO);
//    }
//}
