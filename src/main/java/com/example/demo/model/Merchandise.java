package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

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
}
