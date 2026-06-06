package com.example1.product_management.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example1.product_management.dto.ProductResponseDTO;
import com.example1.product_management.dto.ProductRequestDTO;
import com.example1.product_management.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example1.product_management.exceptions.NotFoundException;
import com.example1.product_management.exceptions.BussinesException;
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
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        if (productService.getProducts().isEmpty()) {
            throw new NotFoundException("No products found", "P-404", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    /**
     * Obtener un producto por ID
     * @param id
     * @return Producto encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductbyId(@PathVariable Long id) {
        ProductResponseDTO product = productService.getProductById(id);
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
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO product) {
        if (product.getName() == "" || product.getPrice() == 0) {
            throw new BussinesException("Name and price are required", "P-400", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    /**
     * Actualizar un producto existente
     * @param id
     * @param product
     * @return Producto actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO product) {
        if (productService.getProductById(id) == null) {
            throw new NotFoundException("Product not found", "P-404", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    /**
     * Eliminar un producto
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id) == null) {
            throw new NotFoundException("Product not found", "P-404", HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtener un producto por nombre
     * @param name
     * @return Producto encontrado
     */

    @GetMapping(params = "name")
    public ResponseEntity<ProductResponseDTO> getProductByName(@RequestParam("name") String name) {
        if (productService.getProductByName(name) == null) {
            throw new NotFoundException("Product not found", "P-404", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.OK);
    }

}