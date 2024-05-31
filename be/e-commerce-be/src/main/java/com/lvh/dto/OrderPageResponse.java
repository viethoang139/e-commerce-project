package com.lvh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPageResponse {
    private Set<OrderDto> orderDtos;
    private int pageNum;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLast;
}
