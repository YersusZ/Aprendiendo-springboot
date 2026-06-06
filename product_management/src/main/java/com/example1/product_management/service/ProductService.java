package com.example1.product_management.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example1.product_management.repository.ProductRepository;
import com.example1.product_management.model.ProductModel;
import com.example1.product_management.dto.ProductRequestDTO;
import com.example1.product_management.dto.ProductResponseDTO;
import com.example1.product_management.mapper.ProductMapper;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {


    private final ProductRepository productsRepository;


    public List<ProductResponseDTO> getProducts() {
        return productsRepository.findAll()
                .stream()
                .map(ProductMapper::toProductResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getProductById(Long id) {
        return productsRepository.findById(id)
                .stream()
                .map(ProductMapper::toProductResponseDTO)
                .findFirst()
                .orElse(null);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {
        ProductModel product = ProductMapper.toProductModelFromRequestDTO(productRequest);
        return ProductMapper.toProductResponseDTO(productsRepository.save(product));
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequest) {
        ProductModel product = ProductMapper.toProductModelFromRequestDTO(productRequest);
        return ProductMapper.toProductResponseDTO(productsRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setStock(product.getStock());
                    return productsRepository.save(existingProduct);
                })
                .orElse(null));
    }

    public void deleteProduct(Long id) {
        productsRepository.deleteById(id);
    }

    public List<ProductResponseDTO> getProductByName(String name) {
        return productsRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ProductMapper::toProductResponseDTO)
                .collect(Collectors.toList());
    }
}

