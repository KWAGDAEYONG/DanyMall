package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Sold {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade={CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_sold_user"))
    private User buyer;

    @OneToOne(cascade={CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_sold_merchandise"))
    private Merchandise soldMerchandise;

    private Date soldDate;


    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public void setSoldMerchandise(Merchandise soldMerchandise) {
        this.soldMerchandise = soldMerchandise;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }
}
