package com.lvh.repository;

import com.lvh.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByProductCategoryId(Long categoryId, Pageable pageable);

    @Query(value = "select p from Product p "+
    " where p.name like concat('%',:name,'%')")
    Page<Product> searchProductByName(String name, Pageable pageable);
}
