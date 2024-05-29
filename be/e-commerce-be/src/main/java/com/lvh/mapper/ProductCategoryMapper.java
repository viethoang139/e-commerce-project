package com.lvh.mapper;

import com.lvh.dto.ProductCategoryDto;
import com.lvh.entity.ProductCategory;

public class ProductCategoryMapper {

    public static ProductCategory mapToProductCategory(ProductCategoryDto productCategoryDto){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(productCategoryDto.getId());
        productCategory.setCategoryName(productCategoryDto.getCategoryName());

        return productCategory;
    }

    public static ProductCategoryDto mapToProductCategoryDto(ProductCategory productCategory){
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        productCategoryDto.setId(productCategory.getId());
        productCategoryDto.setCategoryName(productCategory.getCategoryName());
        return productCategoryDto;
    }

}
