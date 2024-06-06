package com.pos.product.repository;


import com.pos.product.model.Product;

import java.util.List;

public interface ProductRepository {

    public List<Product> allProducts();

    public Product findProduct(String productId);

}