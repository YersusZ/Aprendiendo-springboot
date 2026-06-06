package com.example1.product_management.GlobalException;
import com.example1.product_management.dto.ErrorDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import com.example1.product_management.exceptions.NotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException ex) {
        ErrorDTO error = ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorDTO error = ErrorDTO.builder()
                .code("M-400")
                .message(ex.getBindingResult().getFieldErrors().stream()
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).collect(Collectors.joining(", ")))
                .build();
        return new ResponseEntity<>(error, ex.getStatusCode());
    }

}
