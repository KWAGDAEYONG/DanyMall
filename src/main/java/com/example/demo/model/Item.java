package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue
    Long id;

    private String name;

    @OneToMany(mappedBy = "item")
    private List<Merchandise> merchandise;


    @ManyToMany(mappedBy = "item")
    private List<Cart> cart;

    private String color;
    private String size;
    private int amount;
    private int price;
    private Date release;
    private String detail;
    private String style;
    private String gender;
    private String weather;


    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_item_category"))
    private Category category;
    private String img;


    @Transient
    private List<String> colorList = new ArrayList<String>();
    @Transient
    private List<String> sizeList = new ArrayList<String>();

    public void setColorList() {
        this.colorList.clear();
        String colors[] = this.color.split(",");
        for(String c:colors){
            this.colorList.add(c);
        }
    }

    public void setSizeList() {
        this.sizeList.clear();
        String sizes[] = this.size.split(",");
        for(String s:sizes){
            this.sizeList.add(s);
        }
    }

    public void addCart(Cart cart){
        this.cart.add(cart);
    }

    public Category getCategory() {
        return category;
    }
}
