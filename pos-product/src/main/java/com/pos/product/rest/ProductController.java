package com.pos.product.rest;

import com.pos.product.model.Product;
import com.pos.api.ProductApi;
import com.pos.dto.ProductDto;
import com.pos.product.mapper.ProductMapper;
import com.pos.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController implements ProductApi {

    private final ProductMapper productMapper;

    private final ProductService productService;


    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<List<ProductDto>> listProducts() {
        List<Product> products = this.productService.products();
        Collection<ProductDto> productsCollection = productMapper.toProductsDto(products);
        List<ProductDto> productDtos = new ArrayList<>(productsCollection);
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<ProductDto> showProductById(String productId) {
        Product product = this.productService.getProduct(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductDto productDto = productMapper.toProductDto(product);
        if (productDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDto, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ProductDto> productIdPatch(String id, Integer body) {
        Product product = this.productService.getProduct(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int x = body;
        product.setQuantity(x);
        ProductDto productDto = productMapper.toProductDto(product);
        if (productDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }


}
