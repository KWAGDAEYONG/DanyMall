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
    private int price;
    private Date release;
    private String detail;
    private String style;
    private String gender;
    private String weather;


    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_item_category"))
    private Category category;

    @OneToMany(mappedBy = "item")
    private List<Review> review;

    private String img;


    @Transient
    private List<String> colorList = new ArrayList<String>();
    @Transient
    private List<String> sizeList = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImg(String img) {
        this.img = img;
    }

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

    public Long getId() {
        return id;
    }
}
