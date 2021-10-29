package com.cvdejan.booking.service;

import com.cvdejan.booking.exception.ResourceNotFoundException;
import com.cvdejan.booking.model.City;
import com.cvdejan.booking.repo.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityService {
    private final CityRepo cityRepo;

    @Autowired
    public CityService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public City addCity(City city)   {
            return cityRepo.save(city);
    }

    public List<City> getAllCities(){
        return cityRepo.findAll();
    }

    public City getCity(Long cityId){
        return cityRepo.findById(cityId).orElseThrow(()->new ResourceNotFoundException("City with id " + cityId + " not found")) ;
    }

    public void addCities(List<City> cities) {
        cityRepo.saveAll(cities);
    }
}
