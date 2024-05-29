package com.lvh.mapper;

import com.lvh.dto.ProductDto;
import com.lvh.entity.Product;

public class ProductMapper {

    public static Product mapToProduct(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setSku(productDto.getSku());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setUnitPrice(productDto.getUnitPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setActive(productDto.isActive());
        product.setUnitsInStock(productDto.getUnitsInStock());
        return product;
    }

    public static ProductDto mapToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setSku(product.getSku());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setUnitPrice(product.getUnitPrice());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setActive(product.isActive());
        productDto.setUnitsInStock(product.getUnitsInStock());
        return productDto;
    }

}
