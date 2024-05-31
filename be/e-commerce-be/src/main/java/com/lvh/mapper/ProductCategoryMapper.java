package com.lvh.mapper;

import com.lvh.dto.ProductCategoryDto;
import com.lvh.entity.ProductCategory;

public class ProductCategoryMapper {


    public static ProductCategoryDto mapToProductCategoryDto(ProductCategory productCategory){
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        productCategoryDto.setId(productCategory.getId());
        productCategoryDto.setCategoryName(productCategory.getCategoryName());
        return productCategoryDto;
    }

}
