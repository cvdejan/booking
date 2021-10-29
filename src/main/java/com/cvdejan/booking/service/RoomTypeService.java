package com.cvdejan.booking.service;

import com.cvdejan.booking.exception.ResourceNotFoundException;
import com.cvdejan.booking.model.RoomType;
import com.cvdejan.booking.repo.RoomTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class RoomTypeService {

    private final RoomTypeRepo roomTypeRepo;

    public RoomType addRoomType(RoomType roomType){
        return roomTypeRepo.save(roomType);
    }

    public RoomType findRoomType(Long roomTypeId){
        return roomTypeRepo.findById(roomTypeId).orElseThrow(()->new ResourceNotFoundException("Room not found"));
    }

    public List<RoomType> getAllRoomTypes(){
        return roomTypeRepo.findAll();
    }

    public void addRoomTypes(List<RoomType> roomTypes) {
        roomTypeRepo.saveAllAndFlush(roomTypes);
    }
}
