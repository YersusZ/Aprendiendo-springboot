package com.example1.product_management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example1.product_management.repository.ProductRepository;
import com.example1.product_management.model.ProductModel;
import com.example1.product_management.dto.ProductRequestDTO;
import com.example1.product_management.dto.ProductResponseDTO;
import com.example1.product_management.mapper.ProductMapper;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productsRepository;


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
                    return productsRepository.save(existingProduct);
                })
                .orElseGet(() -> {
                    product.setId(id);
                    return productsRepository.save(product);
                }));
    }

    public void deleteProduct(Long id) {
        productsRepository.deleteById(id);
    }

    public ProductResponseDTO getProductByName(String name) {
        return productsRepository.findAll()
                .stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .map(ProductMapper::toProductResponseDTO)
                .findFirst()
                .orElse(null);
    }
}

