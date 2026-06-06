package com.example1.product_management.exceptions;
import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private String code;
    private HttpStatus status;

    public NotFoundException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
