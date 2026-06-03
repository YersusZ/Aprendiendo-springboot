package com.example1.product_management.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example1.product_management.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
