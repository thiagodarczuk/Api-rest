package com.lab.springboot.controller;

import jakarta.persistence.EntityNotFoundException;
import com.lab.springboot.entity.Product;
import com.lab.springboot.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductService productService;

    // Inyección de dependencias por constructor
    public ProductoController(ProductService productService) {
        this.productService = productService;
    }

    // POST /api/productos
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    // GET /api/productos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET /api/productos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // PUT /api/productos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // DELETE /api/productos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // Manejo de excepción cuando no existe el recurso
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}