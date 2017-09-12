package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Cart implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_cart_item"))
    private List<Item> item;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_cart_user"))
    private User user;

    @Transient
    private int count;

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

    public void setCount(){
        if(item!=null) {
            this.count = this.item.size();
        }
        System.out.println(count);
    }

    public int getCount() {
        return count;
    }

    public boolean alreadyContainItemInCart(Item item){

        for(Item target : this.item){
            if(item.getId()==target.getId()){
                return true;
            }
        }
        return false;

    }

    public List<Item> getItem() {
        return item;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
