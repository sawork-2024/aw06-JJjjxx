package com.pos.carts.service;

import java.util.List;
import java.util.Optional;

import com.pos.carts.model.*;

public interface CartService {

    Double checkout(Cart cart);

    Double checkout(Integer cartId);

    Cart add(Cart cart, Item item);

    List<Cart> getAllCarts();

    Optional<Cart> getCart(Integer id);
    
    Integer test();

}
