package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Merchandise {

    @Id
    private Long id;
    private String name;
    private String number;
    private int price;
    private Date release;
    private String detail;
    private String gender;
    private String style;
    private String weather;
    private String category;
    private String img;
    private String color;
    private String size;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_merchandise_cart"))
    private Cart cart;
    @Transient
    private List<String> colorList = new ArrayList<String>();
    @Transient
    private List<String> sizeList = new ArrayList<String>();

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
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


}
