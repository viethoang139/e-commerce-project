package com.lvh.service.impl;

import com.lvh.dto.PageResponse;
import com.lvh.dto.ProductDto;
import com.lvh.entity.Product;
import com.lvh.exception.ResourceNotFoundException;
import com.lvh.mapper.ProductMapper;
import com.lvh.repository.ProductRepository;
import com.lvh.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> ProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public PageResponse searchProductByName(String name, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<Product> productPage = productRepository.searchProductByName(name, pageable);
        List<Product> products = productPage.getContent();
        List<ProductDto> content = products.stream().map(product -> ProductMapper.mapToProductDto(product)).collect(Collectors.toList());;
        PageResponse pageResponse = new PageResponse();
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
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product","ID",productId.toString()));

        ProductDto productDto = ProductMapper.mapToProductDto(product);
        return productDto;
    }



}
