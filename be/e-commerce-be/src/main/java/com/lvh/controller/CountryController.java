package com.lvh.controller;

import com.lvh.dto.CountryDto;
import com.lvh.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/countries")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/{countryId}")
    public ResponseEntity<CountryDto> findCountryById(@PathVariable Integer countryId){
        return ResponseEntity.ok(countryService.findCountryById(countryId));
    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> findAllCountries(){
        return ResponseEntity.ok(countryService.findAllCountries());
    }

}
