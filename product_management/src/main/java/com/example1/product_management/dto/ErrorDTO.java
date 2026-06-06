package com.example1.product_management.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private String code;
    private String message;
}
