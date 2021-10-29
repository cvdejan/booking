package com.cvdejan.booking;

import com.cvdejan.booking.model.AppUser;
import com.cvdejan.booking.service.AppUserService;
import com.cvdejan.booking.service.HotelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalTime;
import java.util.Arrays;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

/*	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/

	/*
	@Bean
	CommandLineRunner run(AppUserService appUserService){
		return args -> {
			AppUser appUser=appUserService.getUser("dejan");
			appUserService.addUser(appUser);

		};
	} */

/*	@Bean
	CommandLineRunner run(HotelService hotelService){
		return args -> {
			hotelService.saveImageToHotel();

		};
	}*/

}
