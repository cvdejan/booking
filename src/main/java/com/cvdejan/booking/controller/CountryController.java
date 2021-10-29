package com.cvdejan.booking.controller;

import com.cvdejan.booking.model.Country;
import com.cvdejan.booking.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping(path = "/addcountry", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> addCountry(@RequestBody Country country){
        Country newCountry=countryService.addCountry(country);
        return new ResponseEntity<>(newCountry, HttpStatus.CREATED);
    }

    @PostMapping(path = "/addcountries", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCountries(@RequestBody List<Country> countries) {
        countryService.addCountries(countries);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}


