package com.example1.product_management.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example1.product_management.model.ProductModel;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findByNameContainingIgnoreCase(String name);
}
