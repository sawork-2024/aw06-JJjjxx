package com.pos.product.service;

import com.pos.product.model.*;

import java.util.List;

public interface ProductService {


    public List<Product> products();

    public Product getProduct(String id);

    public Product randomProduct();

    
}
