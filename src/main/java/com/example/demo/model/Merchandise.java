package com.example.demo.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Merchandise {
    private Long id;
    private String name;
    private String number;
    private int price;
    private Date release;

    private String gender;
    private String style;
    private String weather;
    private String category;

}
