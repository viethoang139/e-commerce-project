package com.lvh.controller;

import com.lvh.constraint.AppConstraint;
import com.lvh.dto.PageResponse;
import com.lvh.dto.ProductDto;
import com.lvh.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse> searchProductByName(@RequestParam String name,
                                                                @RequestParam(value = "pageNum", required = false, defaultValue = AppConstraint.PAGE_NUM) int pageNum,
                                                                @RequestParam(value = "pageSize", required = false, defaultValue = AppConstraint.PAGE_SIZE) int pageSize){
        return ResponseEntity.ok(productService.searchProductByName(name, pageNum, pageSize));
    }


}
