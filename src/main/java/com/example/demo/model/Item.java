package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;

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

    @Transient
    private int reviewCount;

    @Transient
    private List<Integer> average = new ArrayList<Integer>();

    private String img;


    @Transient
    private List<String> colorList = new ArrayList<String>();
    @Transient
    private List<String> sizeList = new ArrayList<String>();


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
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

    public void updatePrice(int price){
        this.price = price;
    }

    public void setColorAndSize(){
        //색상
        this.colorList.clear();
        String colors[] = this.color.split(",");
        for(String c:colors){
            this.colorList.add(c);
        }
        //사이즈
        this.sizeList.clear();
        String sizes[] = this.size.split(",");
        for(String s:sizes){
            this.sizeList.add(s);
        }
    }

    public void setReviewValue(){
        //별점
        for(Review review:this.review){
            review.setStar();
        }

        //리뷰개수
       this.reviewCount = this.review.size();

        //평점
        int totalCount = reviewCount;
        int totalScore = 0;
        int result = 0;
        for(Review review: this.review){
            totalScore += review.getScore();
        }
        if(totalCount!=0) {
            result = totalScore / totalCount;
        }
        for(int i = 0; i<result; i++){
            this.average.add(1);
        }
    }


    public Long getId() {
        return id;
    }

 /*   public List<Review> getReview() {
        return review;
    }*/

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }
}
