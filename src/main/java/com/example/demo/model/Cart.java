package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(fetch = FetchType.EAGER, mappedBy="cart")
    private List<Merchandise> merchandise;

    public void setId(Long id) {
        this.id = id;
    }

    public void setMerchandise(List<Merchandise> merchandise) {
        this.merchandise = merchandise;
    }



    public void addMerchandise(Merchandise merchandise){
        this.merchandise.add(merchandise);
    }
}
