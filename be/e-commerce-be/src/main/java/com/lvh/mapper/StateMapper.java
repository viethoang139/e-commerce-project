package com.lvh.mapper;

import com.lvh.dto.StateDto;
import com.lvh.entity.State;

public class StateMapper {
    public static StateDto mapToStateDto(State state){
        StateDto stateDto = new StateDto();
        stateDto.setId(state.getId());
        stateDto.setName(state.getName());

        return stateDto;

    }
}
