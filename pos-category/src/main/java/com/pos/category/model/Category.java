package com.pos.category.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Category implements Serializable {

    String id;
    String name;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    


}
