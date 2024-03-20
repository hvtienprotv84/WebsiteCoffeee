package com.tynadmin.service;

import com.tynadmin.exception.NotFoundException;
import com.tynadmin.repository.ProductRepository;
import com.tynadmin.util.FileUploadUtils;
import com.tynadmin.util.RootPathImageUtils;
import com.tynentity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product get(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found product with id: " + id));
    }

    public List<Product> listAll() {
        return (List<Product>) productRepository.findAll();
    }

    public void delete(Integer id) {
        Product product = get(id);
        FileUploadUtils.cleanDir(RootPathImageUtils.PRODUCT + "/" + id + "/");
        FileUploadUtils.delete(RootPathImageUtils.PRODUCT + "/" + id + "/");
        productRepository.delete(product);
    }
}
