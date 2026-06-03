package com.example1.product_management.mapper;
import com.example1.product_management.dto.ProductResponseDTO;
import com.example1.product_management.dto.ProductRequestDTO;
import com.example1.product_management.model.ProductModel;

public class ProductMapper {
    //Product to ProductResponseDTO
    public static ProductResponseDTO toProductResponseDTO(ProductModel product) {
        if (product == null) {
            return null;
        }
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    //ProductResponseDTO to Product
    public static ProductModel toProductModel(ProductResponseDTO productResponseDTO) {
        if (productResponseDTO == null) {
            return null;
        }
        return ProductModel.builder()
                .id(productResponseDTO.getId())
                .name(productResponseDTO.getName())
                .description(productResponseDTO.getDescription())
                .price(productResponseDTO.getPrice())
                .stock(productResponseDTO.getStock())
                .build();
    }

    //ProductRequestDTO to ProductModel
    public static ProductModel toProductModelFromRequestDTO(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO == null) {
            return null;
        }
        return ProductModel.builder()
                .name(productRequestDTO.getName())
                .description(productRequestDTO.getDescription())
                .price(productRequestDTO.getPrice())
                .stock(productRequestDTO.getStock())
                .build();
    }

    //ProductModel to ProductRequestDTO
    public static ProductRequestDTO toProductRequestDTO(ProductModel product) {
        if (product == null) {
            return null;
        }
        return ProductRequestDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
