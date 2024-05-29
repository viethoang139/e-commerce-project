package com.lvh.service;

import com.lvh.dto.CountryDto;
import com.lvh.dto.StateDto;

import java.util.List;

public interface CountryService {

    CountryDto findCountryById(Integer countryId);

    List<CountryDto> findAllCountries();

    List<StateDto> findAllStatesByCountryId(Integer countryId);



}
