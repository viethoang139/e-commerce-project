package com.lvh.service;

import com.lvh.dto.StateDto;

import java.util.List;

public interface StateService {
    StateDto getStateById(Integer id);

    List<StateDto> getAllStates();

    List<StateDto> findAllStatesByCountryId(Integer countryId);

    List<StateDto> findStatesByCountryCode(String code);
}
