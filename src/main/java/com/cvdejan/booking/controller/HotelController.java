package com.cvdejan.booking.controller;

import com.cvdejan.booking.model.Hotel;
import com.cvdejan.booking.service.HotelService;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value="/api/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping(path = "/addhotel", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> addHotel(@Valid @RequestBody Hotel hotel){
        Hotel newHotel=hotelService.addHotel(hotel);
        return new ResponseEntity<>(newHotel, HttpStatus.CREATED);
    }

    @PostMapping(path = "/addhotels", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addHotels(@Valid @RequestBody List<@Valid Hotel> hotels) {
        hotelService.addHotels(hotels);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/gethotel")
    public ResponseEntity<Hotel> getCity(@RequestParam @Min(value=1, message="Hotel Id must be greater than 0") Long hotelId){
        Hotel hotel = hotelService.getHotel(hotelId);
        return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
    }

    //
    @PostMapping(value = "/addimage", consumes={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Boolean> uploadMultiFiles(@RequestPart("file") MultipartFile file,@RequestPart("hotel") Hotel hotel) throws IOException {
        Boolean status=hotelService.saveHotelWithImage(file.getBytes(),hotel);
        return new ResponseEntity<Boolean>(status, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/getimage/{hotelid}")
    public ResponseEntity<byte[]> getHotelImage(@PathVariable("hotelid") Long hotelid) throws IOException {
        byte[] image=hotelService.getHotelImage(hotelid);
        Tika tika=new Tika();
        tika.detect(image);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(tika.detect(image)));
        return new ResponseEntity<byte[]>(image,httpHeaders, HttpStatus.OK);
    }
}
