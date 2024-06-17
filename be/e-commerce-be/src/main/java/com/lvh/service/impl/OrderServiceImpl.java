package com.lvh.service.impl;

import com.lvh.dto.*;
import com.lvh.entity.Address;
import com.lvh.entity.Order;
import com.lvh.entity.OrderItem;
import com.lvh.entity.User;
import com.lvh.mapper.AddressMapper;
import com.lvh.mapper.OrderItemMapper;
import com.lvh.mapper.OrderMapper;
import com.lvh.mapper.UserMapper;
import com.lvh.repository.OrderItemRepository;
import com.lvh.repository.OrderRepository;
import com.lvh.repository.UserRepository;
import com.lvh.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;


    @Override
    public OrderPageResponse getOrderByCustomerEmail(String email, int pageNum, int pageSize) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Can not found user email: " + email));
        UserDto userDto = UserMapper.mapToUserDto(user);
        Set<OrderDto> orderDtoSet = new LinkedHashSet<>();

        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<Order> orderPage = orderRepository.findByUserEmailOrderByDateCreatedDesc(email,pageable);
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
            orderDto.setUser(userDto);
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
