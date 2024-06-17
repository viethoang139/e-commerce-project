package com.lvh.dto;

import com.lvh.entity.*;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private User user;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
