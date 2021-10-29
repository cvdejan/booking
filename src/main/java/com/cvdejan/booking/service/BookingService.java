package com.cvdejan.booking.service;

import com.cvdejan.booking.model.Booking;
import com.cvdejan.booking.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookingService {
    @Autowired
    private BookingRepo bookingRepo;


    public Booking addBooking(Booking booking){
        return bookingRepo.save(booking);
    }

    public List<Booking> getAllBookings(){
        return bookingRepo.findAll();
    }


}
