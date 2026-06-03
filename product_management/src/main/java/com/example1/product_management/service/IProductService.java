package com.example1.product_management.service;
import java.util.List;

import com.example1.product_management.dto.ProductRequestDTO;
import com.example1.product_management.dto.ProductResponseDTO;

public interface IProductService {
    List<ProductResponseDTO> getProducts();
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO createProduct(ProductRequestDTO productRequest);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequest);
    void deleteProduct(Long id);
}