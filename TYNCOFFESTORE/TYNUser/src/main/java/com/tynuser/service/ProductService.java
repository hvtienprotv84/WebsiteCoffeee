package com.tynuser.service;

import com.tynentity.Product;
import com.tynuser.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product get(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found product with id: " + id));
    }

    public List<Product> listAll() {
        return (List<Product>) productRepository.findAll();
    }

}
