package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name ="userId")
    private String userId;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user_cart"))
    private Cart cart;

    @OneToOne(mappedBy = "buyer")
    private Sold sold;

    @OneToMany(mappedBy = "writer")
    private List<Review> review;


    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSamePassword(String password){
        if(!this.password.equals(password)){
            return false;
        }
        return true;
    }

    public void update(User user){

        this.userId = user.userId;
        this.password = user.password;
        this.name = user.name;
        this.email = user.email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
