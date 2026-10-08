package com.lab.springboot.service;

import com.lab.springboot.entity.Product;

import java.util.List;

public interface ProductService {
    //create
    public Product createProduct(Product product);

    //read
    public List<Product> getAllProducts();
    public Product getProductById(Long productId);

    //update
    public Product updateProduct(Long productId, Product product);

    //delete
    public Product deleteProductById(Long productId);

}