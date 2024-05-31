package com.lvh.service.impl;


import com.lvh.dto.ProductCategoryDto;
import com.lvh.dto.ProductDto;
import com.lvh.dto.ProductPageResponse;
import com.lvh.entity.Product;
import com.lvh.entity.ProductCategory;
import com.lvh.exception.ResourceNotFoundException;
import com.lvh.mapper.ProductCategoryMapper;
import com.lvh.mapper.ProductMapper;
import com.lvh.repository.ProductCategoryRepository;
import com.lvh.repository.ProductRepository;
import com.lvh.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;


    @Override
    public List<ProductCategoryDto> getAllCategories() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        return productCategories.stream().map(ProductCategoryMapper::mapToProductCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryDto getCategoryById(Long categoryId) {
        ProductCategory productCategory = productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","ID",categoryId.toString()));
        return ProductCategoryMapper.mapToProductCategoryDto(productCategory);
    }

    @Override
    public ProductPageResponse findAllProductWithCategoryId(int pageNum, int pageSize, Long categoryId) {

        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<Product> productPage = productRepository.findByProductCategoryId(categoryId, pageable);
        List<Product> products = productPage.getContent();
        List<ProductDto> content = products.stream().map(ProductMapper::mapToProductDto).collect(Collectors.toList());
        ProductPageResponse pageResponse = new ProductPageResponse();
        pageResponse.setProductDtos(content);
        pageResponse.setPageNum(pageNum);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotalPages(productPage.getTotalPages());
        pageResponse.setTotalElements(productPage.getTotalElements());
        pageResponse.setLast(productPage.isLast());
        return pageResponse;
    }


}
