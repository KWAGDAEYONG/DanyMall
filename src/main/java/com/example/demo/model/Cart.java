package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_cart_item"))
    private List<Item> item;

    public void setId(Long id) {
        this.id = id;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public void addItem(Item item){
        this.item.clear();
        this.item.add(item);
    }

    public List<Item> getItem() {
        return item;
    }
}
