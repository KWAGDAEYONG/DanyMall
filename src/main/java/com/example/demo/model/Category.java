package com.example.demo.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String part;

    @OneToMany(mappedBy = "category")
    private List<Item> item;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPart(String part) {
        this.part = part;
    }



}
