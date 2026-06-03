package com.example1.product_management.dto;
import lombok.Getter;
import lombok.Setter;   
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Integer stock;

}
