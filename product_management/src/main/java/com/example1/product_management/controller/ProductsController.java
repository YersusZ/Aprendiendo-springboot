package com.example1.product_management.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example1.product_management.dto.ProductResponseDTO;
import com.example1.product_management.dto.ProductRequestDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example1.product_management.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import com.example1.product_management.service.IProductService;


/*GET/api/v1/products — listar todos los productos
GET/api/v1/products/{id} — obtener un producto por ID
POST/api/v1/products — crear un producto
PUT/api/v1/products/{id} — actualizar un producto
DELETE/api/v1/products/{id} — eliminar un producto*/

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductsController {

    private final IProductService IProductService;

    /**
     * Listar todos los productos
     * @return Lista de productos
     */
    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        return new ResponseEntity<>(IProductService.getProducts(), HttpStatus.OK);
    }

    /**
     * Obtener un producto por ID
     * @param id
     * @return Producto encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductbyId(@PathVariable Long id) {
        ProductResponseDTO product = IProductService.getProductById(id);
        if (product == null) {
            throw new NotFoundException("Product not found", "P-404", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Crear un nuevo producto
     * @param product
     * @return Producto creado
     */
    @PostMapping()
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO product) {
        return new ResponseEntity<>(IProductService.createProduct(product), HttpStatus.CREATED);
    }

    /**
     * Actualizar un producto existente
     * @param id
     * @param product
     * @return Producto actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO product) {
        ProductResponseDTO existingProduct = IProductService.getProductById(id);
        if (existingProduct == null) {
            throw new NotFoundException("Product not found", "P-404", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(IProductService.updateProduct(id, product), HttpStatus.OK);
    }

    /**
     * Eliminar un producto
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        ProductResponseDTO existingProduct = IProductService.getProductById(id);
        if (existingProduct == null) {
            throw new NotFoundException("Product not found", "P-404", HttpStatus.NOT_FOUND);
        }
        IProductService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtener un producto por nombre
     * @param name
     * @return Producto encontrado
     */

    @GetMapping(params = "name")
    public ResponseEntity<List<ProductResponseDTO>> getProductByName(@RequestParam("name") String name) {
        List<ProductResponseDTO> products = IProductService.getProductByName(name);
        if (products.isEmpty()) {
            throw new NotFoundException("Product not found", "P-404", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}