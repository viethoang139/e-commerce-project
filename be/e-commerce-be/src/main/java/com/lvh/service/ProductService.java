package com.lvh.service;

import com.lvh.dto.PageResponse;
import com.lvh.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    PageResponse searchProductByName(String name, int pageNum, int pageSize);

    ProductDto getProductById(Long productId);



}
