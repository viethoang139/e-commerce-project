package com.lvh.mapper;

import com.lvh.dto.CustomerDto;
import com.lvh.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }
}
