package com.cvdejan.booking.service;

import com.cvdejan.booking.model.Country;
import com.cvdejan.booking.repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CountryService {
    @Autowired
    private CountryRepo countryRepo;

    public Country addCountry(Country country){
        return countryRepo.save(country);
    }

    public List<Country> getAllCountries(){
        return countryRepo.findAll();
    }

    public void addCountries(List<Country> countries){
        countryRepo.saveAllAndFlush(countries);
    }

}
