package com.KpApp.SpringBootApp.repo;

import com.KpApp.SpringBootApp.model.Product_type_dimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Product_type_dimensionRepo extends JpaRepository<Product_type_dimension, String> {

    @Query(value = "select * from product_type_dimension where product_type = ?1",nativeQuery = true)
    Product_type_dimension findProductTypeDimensionByProductType(String product_type);
    @Query(value = "select * from product_type_dimension where product_type like ?1",nativeQuery = true)
    Iterable<Product_type_dimension> findProductTypeDimensionByProductTypeContaing(String id);
}
