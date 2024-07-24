package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.Hotel;
import com.example.demo.service.HotelService;
import com.example.demo.utils.VarList;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/travel/hotel")
@AllArgsConstructor
public class HotelController {
    private final HotelService hotelService;
    private final ResponseDTO responseDTO;
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO> createHotel(@RequestBody Hotel hotel){
        try{
            String message = hotelService.createHotel(hotel);
            if(message.equals("Not valid")){
                responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
                responseDTO.setMessage(message);
                responseDTO.setContent(null);
            } else if (message.equals("Saved")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Hotel "+message);
                responseDTO.setContent(null);
            }
        }
        catch (Exception e){
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("Something went wrong");
            responseDTO.setContent(null);
        }

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/getAllHotels")
    public ResponseEntity<ResponseDTO> getAllHotels(){
        try{
            List<Hotel> hotelList = hotelService.getAllHotels();

            if(!hotelList.isEmpty()) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Listing all hotels");
                responseDTO.setContent(hotelList);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No hotels found");
                responseDTO.setContent(null);
            }
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/hotel/{id}")
    public ResponseEntity<ResponseDTO> getHotelById(@PathVariable long id){
        if(id < 0){
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Invalid hotel id");
            responseDTO.setContent(null);
        }
        else {
            try {
                Hotel hotel = hotelService.getHotelById(id);
                if(hotel==null){
                    responseDTO.setCode(VarList.RSP_FAIL);
                    responseDTO.setMessage("No hotel found");
                    responseDTO.setContent(null);
                }
                else {
                    responseDTO.setCode(VarList.RSP_SUCCESS);
                    responseDTO.setMessage("Hotel found");
                    responseDTO.setContent(hotel);
                }
            }
            catch (Exception e){
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Something went wrong");
                responseDTO.setContent(null);
            }
        }
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateHotel(@RequestBody Hotel hotel){
        if(hotel == null || hotel.getId() <0){
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Invalid hotel data");
            responseDTO.setContent(null);
        }
        else {
            try {
                String message = hotelService.updateHotel(hotel);

                if (message.equals("Not valid")){
                    responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
                    responseDTO.setMessage("Invalid hotel data");
                    responseDTO.setContent(null);
                } else {
                    responseDTO.setCode(VarList.RSP_SUCCESS);
                    responseDTO.setMessage("Hotel data updated");
                    responseDTO.setContent(null);
                }
            }
            catch (Exception e){
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Somthing wennt wrong");
                responseDTO.setContent(null);
            }
        }
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteHotel(@PathVariable long id){
        if (id<0){
            responseDTO.setCode(VarList.RSP_NOT_AUTHORISED);
            responseDTO.setMessage("Invalid hotel id");
            responseDTO.setContent(null);
        }
        else {
            try{
                hotelService.deleteHotelById(id);
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Hotel deleted");
                responseDTO.setContent(null);
            }
            catch (Exception e){
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Hotel deletion faild");
                responseDTO.setContent(null);
            }
        }
        return ResponseEntity.ok(responseDTO);
    }


}
