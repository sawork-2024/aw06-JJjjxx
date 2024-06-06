package com.pos.carts.repository;

import org.springframework.data.repository.CrudRepository;
import com.pos.carts.model.Cart;



public interface CartRepository extends CrudRepository<Cart, Integer>{

}
