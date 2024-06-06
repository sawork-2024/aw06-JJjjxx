package com.pos.carts.model;



import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "carts")
@Accessors(fluent = true, chain = true)
@Data
public class Cart implements Serializable {
    
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "items", joinColumns = @JoinColumn(name = "cart_id") )
    private List<Item> items = new ArrayList<>();


    public boolean addItems(Item item) {
        return items.add(item);
    }

    public boolean removeItems(Item item) {
        return items.remove(item);
    }



}
