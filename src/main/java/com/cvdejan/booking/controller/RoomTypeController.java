package com.cvdejan.booking.controller;

import com.cvdejan.booking.model.RoomType;
import com.cvdejan.booking.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/api/roomtype", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    @PostMapping(path = "/addroomtype", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomType> addRoomType(@RequestBody RoomType roomType){
        RoomType newRoomType=roomTypeService.addRoomType(roomType);
        return new ResponseEntity<>(newRoomType, HttpStatus.CREATED);
    }

    @PostMapping(path = "/addroomtypes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRoomTypes(@RequestBody List<@Valid RoomType> roomTypes){
        roomTypeService.addRoomTypes(roomTypes);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/getroomtypes")
    public ResponseEntity<List<RoomType>> getRoomTypes(){
        List<RoomType> allRoomTypes=roomTypeService.getAllRoomTypes();
        return new ResponseEntity<>(allRoomTypes, HttpStatus.OK);
    }
}

