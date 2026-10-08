package com.lab.springboot.service;

import jakarta.persistence.EntityNotFoundException;
import com.lab.springboot.entity.Product;
import com.lab.springboot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // Inyección por constructor (práctica recomendada en Spring)
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        return productRepository.save(existingProduct);
    }

    @Override
    public Product deleteProductById(Long productId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));

        productRepository.delete(existingProduct);
        return existingProduct;
    }
}