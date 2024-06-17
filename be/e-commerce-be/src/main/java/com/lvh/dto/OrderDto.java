package com.lvh.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;
    private Date dateCreated;
    private Date lastUpdated;
    private Set<OrderItemDto> orderItems;
    private UserDto user;
    private AddressDto shippingAddress;
    private AddressDto billingAddress;
}
