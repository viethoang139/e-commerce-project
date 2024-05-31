package com.lvh.mapper;

import com.lvh.dto.AddressDto;
import com.lvh.entity.Address;

public class AddressMapper {
    public static AddressDto mapToAddressDto (Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());
        addressDto.setZipCode(address.getZipCode());
        return addressDto;
    }
}
