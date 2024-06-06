package com.pos.carts.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pos.carts.mapper.CartMapper;
import com.pos.carts.model.Cart;
import com.pos.carts.model.Item;
import com.pos.carts.repository.CartRepository;
import com.pos.dto.CartDto;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    
    private CartMapper cartMapper;

    private final String COUNTER_URL = "http://POS-COUNTER/counter/";

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @LoadBalanced
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    public void setcCircuitBreakerFactory(CircuitBreakerFactory circuitBreakerFactory) {
        this.circuitBreakerFactory = circuitBreakerFactory;
    }


    @Override
    public Double checkout(Cart cart) {
        return circuitBreakerFactory.create("checkout").run(() -> {
            CartDto cartDto = cartMapper.toCartDto(cart);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = null;
            try {
                request = new HttpEntity<>(mapper.writeValueAsString(cartDto), headers);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Double total = restTemplate.postForObject(COUNTER_URL + "/checkout", request, Double.class);
            return total;
        }, throwable -> checkoutFallback());
    }
    // 供断路器调用的方法
    private Double checkoutFallback() {
        return -1.0;
    }


@Override
public Double checkout(Integer cartId) {
    Optional<Cart> cart = this.cartRepository.findById(cartId);

    if (cart.isEmpty())
        return Double.valueOf(-1);

    // Use circuit breaker in checkout(Integer cartId) method
    return circuitBreakerFactory.create("checkout").run(() -> this.checkout(cart.get()), throwable -> checkoutFallback());
}
    
    @Override
    public Cart add(Cart cart, Item item) {
        if (cart.addItems(item))
            return cartRepository.save(cart);
        return null;
    }

    @Override
    public List<Cart> getAllCarts() {
        return Streamable.of(cartRepository.findAll()).toList();
    }
    @Override
    public Optional<Cart> getCart(Integer cartId) {
        return cartRepository.findById(cartId);
    }
    @Override
    public Integer test() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    }
    
}
