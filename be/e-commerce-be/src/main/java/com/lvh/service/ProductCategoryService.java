package com.lvh.service;


import com.lvh.dto.ProductCategoryDto;
import com.lvh.dto.ProductPageResponse;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategoryDto> getAllCategories();

    ProductCategoryDto getCategoryById(Long categoryId);

    ProductPageResponse findAllProductWithCategoryId(int pageNum, int pageSize, Long categoryId);
}
