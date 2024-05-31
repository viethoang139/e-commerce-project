package com.lvh.mapper;

import com.lvh.dto.OrderItemDto;
import com.lvh.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItemDto mapToOrderItemDto(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setImageUrl(orderItem.getImageUrl());
        orderItemDto.setUnitPrice(orderItem.getUnitPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setProductId(orderItem.getProductId());
        return orderItemDto;
    }
}
