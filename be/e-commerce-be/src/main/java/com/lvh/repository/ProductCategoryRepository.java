package com.lvh.repository;

import com.lvh.entity.Product;
import com.lvh.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

}
