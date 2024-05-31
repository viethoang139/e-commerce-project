package com.lvh.service;


import com.lvh.dto.ProductDto;
import com.lvh.dto.ProductPageResponse;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductPageResponse searchProductByName(String name, int pageNum, int pageSize);

    ProductDto getProductById(Long productId);



}
