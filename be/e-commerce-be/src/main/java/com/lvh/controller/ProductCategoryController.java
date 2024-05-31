package com.lvh.controller;

import com.lvh.constraint.AppConstraint;
import com.lvh.dto.ProductCategoryDto;
import com.lvh.dto.ProductPageResponse;
import com.lvh.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<List<ProductCategoryDto>> getAllCategories(){
        return ResponseEntity.ok(productCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> getCategoryById(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(productCategoryService.getCategoryById(categoryId));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductPageResponse> getAllProductsByCategoryId(@PathVariable("id") Long categoryId,
                                                                          @RequestParam(value = "pageNum", required = false, defaultValue = AppConstraint.PAGE_NUM) int pageNum,
                                                                          @RequestParam(value = "pageSize", required = false, defaultValue = AppConstraint.PAGE_SIZE) int pageSize
    ){
        return ResponseEntity.ok(productCategoryService.findAllProductWithCategoryId(pageNum, pageSize,categoryId));
    }

}
