package com.lvh.service.impl;

import com.lvh.dto.ProductDto;
import com.lvh.dto.ProductPageResponse;
import com.lvh.entity.Product;
import com.lvh.mapper.ProductMapper;
import com.lvh.repository.ProductRepository;
import com.lvh.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream().map(ProductMapper::mapToProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductPageResponse searchProductByName(String name, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<Product> productPage = productRepository.searchProductByName(name, pageable);
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

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).
                orElseThrow(() -> new NoSuchElementException("Not found product with ID: " + productId));
        return ProductMapper.mapToProductDto(product);
    }



}
