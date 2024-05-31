package com.lvh.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
