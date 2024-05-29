package com.lvh.mapper;

import com.lvh.dto.CountryDto;
import com.lvh.entity.Country;

public class CountryMapper {

    public static CountryDto mapToCountryDto(Country country){
        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setCode(country.getCode());
        countryDto.setName(country.getName());
        return countryDto;
    }
}
