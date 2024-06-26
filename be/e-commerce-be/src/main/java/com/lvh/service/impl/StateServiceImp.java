package com.lvh.service.impl;

import com.lvh.dto.StateDto;
import com.lvh.entity.State;
import com.lvh.mapper.StateMapper;
import com.lvh.repository.StateRepository;
import com.lvh.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StateServiceImp implements StateService {
    private final StateRepository stateRepository;


    @Override
    public StateDto getStateById(Integer id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found state with ID: " + id));
        return StateMapper.mapToStateDto(state);
    }
    @Override
    public List<StateDto> getAllStates() {
        List<State> states = stateRepository.findAll();
        return states.stream().map(StateMapper::mapToStateDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StateDto> findAllStatesByCountryId(Integer countryId) {
        List<State> states = stateRepository.findStateByCountryId(countryId);
        return states.stream().map(StateMapper::mapToStateDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StateDto> findStatesByCountryCode(String code) {
        List<State> states = stateRepository.findStateByCountryCode(code);

        return states.stream().map(StateMapper::mapToStateDto)
                .collect(Collectors.toList());

    }
}











