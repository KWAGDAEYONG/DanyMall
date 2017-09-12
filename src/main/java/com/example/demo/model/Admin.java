package com.example.demo.model;

import com.example.demo.security.GoogleUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Admin {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;

    public void setGmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin(String name, String email) {
        return (((email).equals(this.email)) &&((name).equals(this.name)));
    }
}
