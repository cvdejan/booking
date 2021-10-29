package com.cvdejan.booking.service;

import com.cvdejan.booking.exception.ResourceNotFoundException;
import com.cvdejan.booking.model.Hotel;
import com.cvdejan.booking.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.nio.file.Paths.get;

@Transactional
@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;

    public Hotel addHotel(Hotel hotel){
        return hotelRepo.save(hotel);
    }

    public void addHotels(List<Hotel> hotels) {
         hotelRepo.saveAll(hotels);
    }

    public Hotel getHotel(Long hotelId){
        return hotelRepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with id: "+hotelId+"not found"));
    }
/*

    public void addImageToHotel() throws IOException {
        Hotel hotel=getHotel(1L);

        String filePath=System.getProperty("user.home")+"/Downloads/uploads/hotel1.jpg";

        byte[] file=Files.readAllBytes(get(filePath));

        hotel.setHotelImage(file);
        hotelRepo.save(hotel);
    }

    public void saveImageToHotel() throws IOException {
        Hotel hotel=getHotel(2L);

        String filePath=System.getProperty("user.home")+"/Downloads/uploads/hotelcopy.jpg";
        byte[] image=hotel.getHotelImage();
        Files.write(get(filePath),image);
    }
*/

    public Boolean saveHotelWithImage(byte[] image, Hotel hotel) {
        String hotelDesc=hotel.getHotelDescription();
        String hotelLocation=hotel.getHotelLocation();
        hotel=getHotel(hotel.getId());
        hotel.setHotelImage(image);
        if(hotelDesc!=null) hotel.setHotelDescription(hotelDesc);
        if(hotelLocation!=null) hotel.setHotelLocation(hotelLocation);
        hotelRepo.save(hotel);
        return true;
    }

    public byte[] getHotelImage(Long hotelId){
        return getHotel(hotelId).getHotelImage();
    }
}
