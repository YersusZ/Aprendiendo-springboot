package com.example1.product_management.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example1.product_management.dto.ProductResponseDTO;
import com.example1.product_management.dto.ProductRequestDTO;
import com.example1.product_management.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


/*GET/api/v1/products — listar todos los productos
GET/api/v1/products/{id} — obtener un producto por ID
POST/api/v1/products — crear un producto
PUT/api/v1/products/{id} — actualizar un producto
DELETE/api/v1/products/{id} — eliminar un producto*/

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    /**
     * Listar todos los productos
     * @return Lista de productos
     */
    @GetMapping()
    public List<ProductResponseDTO> Products() {
        return productService.getProducts();
    }

    /**
     * Obtener un producto por ID
     * @param id
     * @return Producto encontrado
     */
    @GetMapping("/{id}")
    public ProductResponseDTO getProductbyId(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    /**
     * Crear un nuevo producto
     * @param product
     * @return Producto creado
     */
    @PostMapping()
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO product) {
        return productService.createProduct(product);
    }

    /**
     * Actualizar un producto existente
     * @param id
     * @param product
     * @return Producto actualizado
     */
    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO product) {
        return productService.updateProduct(id, product);
    }

    /**
     * Eliminar un producto
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}