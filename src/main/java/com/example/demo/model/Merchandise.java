package com.example.demo.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Merchandise {

    @Id
    private Long id;
    private String number;
    private String color;
    private String size;
    private int amount;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_merchandise_item"))
    private Item item;

    //@ManyToMany(mappedBy = "buyList")

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_merchandise_user"))
    private List<User> buyerList;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean choolgo(){
        if(this.amount==0){
            return false;
        }
        this.amount = this.amount-1;
        return true;
    }

    public void addBuyer(User user){
        this.buyerList.add(user);
    }

}
