package com.cvdejan.booking.controller;

import com.cvdejan.booking.model.City;
import com.cvdejan.booking.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value="/api/city", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping(path = "/addcity", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> addCity(@Valid @RequestBody City city){
        City newCity=cityService.addCity(city);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }

    @PostMapping(path = "/addcities", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCountries(@Valid @RequestBody List<@Valid City> cities) {
        cityService.addCities(cities);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/getcities")
    public ResponseEntity<List<City>> getCities(){
        List<City> allCities=cityService.getAllCities();
        return new ResponseEntity<>(allCities, HttpStatus.OK);
    }

    @GetMapping(path="/getcity")
    public ResponseEntity<City> getCity(@RequestParam @Min(value=1, message="City Id must be greater than 0") Long cityId){
            City city = cityService.getCity(cityId);
            return new ResponseEntity<City>(city, HttpStatus.OK);
        }
    }