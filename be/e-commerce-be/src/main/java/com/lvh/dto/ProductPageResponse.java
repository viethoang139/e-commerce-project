package com.lvh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageResponse {
    private List<ProductDto> productDtos;
    private int pageNum;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLast;
}
