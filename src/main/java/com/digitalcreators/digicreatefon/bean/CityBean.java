package com.digitalcreators.digicreatefon.bean;

import antlr.ASTNULLType;
import com.digitalcreators.digicreatefon.model.City;
import com.digitalcreators.digicreatefon.repository.CityRepository;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Controller(value = "cityBean")
public class CityBean {



    @Autowired
    private CityRepository cityRepository;

    private List<City> allCities;
    private List<City> selectedCities;
    private City selectedCity;

    private Long selectedCityId;



    @PostConstruct
    public void init() {
        allCities = cityRepository.findAll();
        selectedCities = new ArrayList<>();
    }

    public List<City> getAllCities() {
        return allCities;
    }

    public List<City> getSelectedCities() {
        return selectedCities;
    }

    public void setSelectedCities(List<City> selectedCities) {
        this.selectedCities = selectedCities;
    }

    public void onCityChange() {
        City selectedCity = cityRepository.findById(selectedCityId).orElse(null);
    }



}
