package com.lvh.service;

import com.lvh.dto.PageResponse;
import com.lvh.dto.ProductCategoryDto;
import com.lvh.dto.ProductDto;
import com.lvh.entity.Product;
import com.lvh.entity.ProductCategory;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategoryDto> getAllCategories();

    ProductCategoryDto getCategoryById(Long categoryId);

    PageResponse findAllProductWithCategoryId(int pageNum, int pageSize,Long categoryId);
}
