package com.pos.carts.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "items")
@Accessors(fluent = true, chain = true)
public class Item implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "cart_Id")
    private Integer cartId;

    @Column(name = "product_Id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "quantity")
    private int quantity;

}
