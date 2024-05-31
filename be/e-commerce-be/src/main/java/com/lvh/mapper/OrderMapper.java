package com.lvh.mapper;

import com.lvh.dto.OrderDto;
import com.lvh.entity.Order;

public class OrderMapper {

    public static OrderDto mapToOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderDto.setTotalQuantity(order.getTotalQuantity());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setStatus(order.getStatus());
        orderDto.setDateCreated(order.getDateCreated());
        orderDto.setLastUpdated(order.getLastUpdated());
        return orderDto;
    }


}
