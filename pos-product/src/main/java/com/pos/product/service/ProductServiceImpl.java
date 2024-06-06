package com.pos.product.service;


import com.pos.product.model.Product;
import com.pos.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j // Lombok annotation to autocreate an Slf4j-based LoggerFactory as log
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable(value = "products")
    public List<Product> products() {
        log.info("Fetching all products");
        return productRepository.allProducts();
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public Product getProduct(String id) {
        log.info("Fetching product with id: {}", id);
        return productRepository.findProduct(id);
    }

    @Override
    public Product randomProduct() {
        return null;
    }


}
