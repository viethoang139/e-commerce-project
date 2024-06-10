package com.lvh.service.impl;

import com.lvh.dto.CountryDto;
import com.lvh.entity.Country;
import com.lvh.mapper.CountryMapper;
import com.lvh.repository.CountryRepository;
import com.lvh.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;


    @Override
    public CountryDto findCountryById(Integer countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NoSuchElementException("Not found country with ID: " + countryId));
        return CountryMapper.mapToCountryDto(country);
    }

    @Override
    public List<CountryDto> findAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream().map(CountryMapper::mapToCountryDto)
                .collect(Collectors.toList());
    }

}
