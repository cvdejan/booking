package com.cvdejan.booking.controller;

import com.cvdejan.booking.model.Booking;
import com.cvdejan.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/booking", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping(path = "/addbooking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> addCity(@RequestBody Booking booking){
        Booking newBooking=bookingService.addBooking(booking);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @GetMapping(path="/getbookings")
    public ResponseEntity<List<Booking>> getCities(){
        List<Booking> allBookings=bookingService.getAllBookings();
        return new ResponseEntity<>(allBookings, HttpStatus.OK);
    }
}