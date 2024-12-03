package com.example.demo.controller;


import com.example.demo.dto.item.ItemDTO;
import com.example.demo.dto.item.ItemListDTO;
import com.example.demo.dto.item.ItemRequestDTO;
import com.example.demo.entity.Item;
import com.example.demo.repo.ShopRepository;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(path = "api/travel/item")
@RequiredArgsConstructor
public class ItemController {

    private final ShopRepository shopRepository;
    private final ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<?> createItem(@RequestBody ItemRequestDTO itemRequestDTO) {
        try {
            // Get the Shop object from the Shop ID
            Item newItem = itemService.saveItem(itemRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Item created successfully!");
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating shop: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItem(
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("price") String price,
            @RequestPart("itemCount") String itemCount,
            @RequestPart("shopID") String shopID,
            @RequestPart("category") String category,
            @RequestPart("coverImage") MultipartFile coverImage) {

        // Validate cover image
        if (coverImage == null || coverImage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cover image file is empty");
        }

        // Validate other inputs
        try {
            if (name == null || name.isBlank() ||
                    description == null || description.isBlank() ||
                    price == null || Float.parseFloat(price) <= 0 ||
                    itemCount == null || Integer.parseInt(itemCount) < 0 ||
                    shopID == null || Long.parseLong(shopID) <= 0 ||
                    category == null || category.isBlank()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid input data");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid number format for price, item count, or shop ID");
        }


        try {
            // Define a base directory for uploads
            String baseDir = "uploads/item";
            // Ensure the upload directory exists
            Path uploadPath = Paths.get(baseDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a unique coverImage name to avoid conflicts
            String coverImageName = coverImage.getOriginalFilename();
            String uniqueCoverImageName = System.currentTimeMillis() + "_" + coverImageName;;

            // Define the full coverImage path
            Path coverImagePath = uploadPath.resolve(uniqueCoverImageName);

            // Save the coverImage
            Files.copy(coverImage.getInputStream(), coverImagePath);

            Long shopIDLong = Long.parseLong(shopID);
            Float priceFloat = Float.parseFloat(price);
            int itemCountInt = Integer.parseInt(itemCount);

            ItemRequestDTO itemRequestDTO = new ItemRequestDTO();
            itemRequestDTO.setName(name);
            itemRequestDTO.setDescription(description);
            itemRequestDTO.setPrice(priceFloat);
            itemRequestDTO.setItem_count(itemCountInt);
            itemRequestDTO.setItem_category(category);
            itemRequestDTO.setShopID(shopIDLong);
            itemRequestDTO.setCover_image(coverImagePath.toString().replace("\\", "/"));
            itemRequestDTO.setIs_available(true);

            Item item = itemService.addItem(itemRequestDTO);
            return ResponseEntity.ok(item);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process item creation");
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

    @GetMapping("/allItemsByShopID/{id}")
    public ResponseEntity<?> getAllItemsByShopID(@PathVariable String id) {
        try {
            Long shopID = Long.parseLong(id);
            List<ItemListDTO> items = itemService.getAllItemsByShopID(shopID);

            return ResponseEntity.ok(items);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching products: " + e.getMessage());
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

    @GetMapping("itemOnly/{id}")
    public ResponseEntity<?> getItemOnlyById(@PathVariable String id) {
        try {
            long itemID = Long.parseLong(id);
            ItemDTO item = itemService.getItemOnlyById(itemID);
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

    @GetMapping("coverImage/{id}")
    public ResponseEntity<?> getCoverImage(@PathVariable String id) {
        try {
            long itemID = Long.parseLong(id);
            String cover = itemService.getCoverImage(itemID);
            return ResponseEntity.ok(cover);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching item: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateItem(@PathVariable long id, @RequestBody ItemRequestDTO itemRequestDTO) {
        try {
            Item updatedItem = itemService.updateItem(id, itemRequestDTO);
            return ResponseEntity.ok("Item updated successfully!");
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating shop: " + e.getMessage());
        }
    }

    @PutMapping("/updateCover/{id}")
    public ResponseEntity<?> addItem(@PathVariable String id, @RequestPart("coverImage") MultipartFile coverImage) {

        if (coverImage == null || coverImage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cover image file is empty");
        }

        try {
            String baseDir = "uploads/item";
            Path uploadPath = Paths.get(baseDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String coverImageName = coverImage.getOriginalFilename();
            String uniqueCoverImageName = System.currentTimeMillis() + "_" + coverImageName;;

            Path coverImagePath = uploadPath.resolve(uniqueCoverImageName);
            Files.copy(coverImage.getInputStream(), coverImagePath);

            String cover = coverImagePath.toString().replace("\\", "/");
            Long itemID = Long.parseLong(id);

            String success = itemService.updateCoverImage(itemID, cover);
            return ResponseEntity.ok(success);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process item creation");
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
