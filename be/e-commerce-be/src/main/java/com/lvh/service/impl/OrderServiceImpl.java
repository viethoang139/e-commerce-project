package com.lvh.service.impl;

import com.lvh.dto.*;
import com.lvh.entity.Address;
import com.lvh.entity.Customer;
import com.lvh.entity.Order;
import com.lvh.entity.OrderItem;
import com.lvh.mapper.AddressMapper;
import com.lvh.mapper.CustomerMapper;
import com.lvh.mapper.OrderItemMapper;
import com.lvh.mapper.OrderMapper;

import com.lvh.repository.CustomerRepository;
import com.lvh.repository.OrderItemRepository;
import com.lvh.repository.OrderRepository;
import com.lvh.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;


    @Override
    public OrderPageResponse getOrderByCustomerEmail(String email, int pageNum, int pageSize) {

        Customer customer = customerRepository.findByEmail(email);
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer);
        Set<OrderDto> orderDtoSet = new LinkedHashSet<>();

        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<Order> orderPage = orderRepository.findByCustomerEmailOrderByDateCreatedDesc(email,pageable);
        List<Order> orderList = orderPage.getContent();
        for(Order order : orderList){
            Address billingAddress = order.getBillingAddress();
            Address shippingAddress = order.getShippingAddress();
            AddressDto billingAddressDto = AddressMapper.mapToAddressDto(billingAddress);
            AddressDto shippingAddressDto = AddressMapper.mapToAddressDto(shippingAddress);
            OrderDto orderDto = OrderMapper.mapToOrderDto(order);
            Set<OrderItem> orderItems = orderItemRepository.findByOrderId(orderDto.getId());
            Set<OrderItemDto> orderItemDtos = orderItems.stream().map(OrderItemMapper::mapToOrderItemDto)
                    .collect(Collectors.toSet());
            orderDto.setOrderItems(orderItemDtos);
            orderDto.setCustomer(customerDto);
            orderDto.setBillingAddress(billingAddressDto);
            orderDto.setShippingAddress(shippingAddressDto);
            orderDtoSet.add(orderDto);
        }
        OrderPageResponse orderPageResponse = new OrderPageResponse();
        orderPageResponse.setOrderDtos(orderDtoSet);
        orderPageResponse.setPageNum(pageNum);
        orderPageResponse.setPageSize(pageSize);
        orderPageResponse.setTotalPages(orderPage.getTotalPages());
        orderPageResponse.setTotalElements(orderPage.getTotalElements());
        orderPageResponse.setLast(orderPage.isLast());
        return orderPageResponse;
    }

}
