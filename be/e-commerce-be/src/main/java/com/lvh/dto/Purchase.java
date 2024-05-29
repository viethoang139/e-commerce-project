package com.lvh.dto;

import com.lvh.entity.Address;
import com.lvh.entity.Customer;
import com.lvh.entity.Order;
import com.lvh.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
