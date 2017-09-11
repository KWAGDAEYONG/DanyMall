package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Sold{

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

    private String deliveryState = "배송대기중";

    private int qty;

    private int totalPayment;

    public Sold(User buyer, Merchandise soldMerchandise, Date date, int qty){
        this.buyer = buyer;
        this.soldMerchandise = soldMerchandise;
        this.soldDate = date;
        this.qty = qty;

        this.totalPayment = (this.soldMerchandise.getItem().getPrice())*this.qty;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public void setSoldMerchandise(Merchandise soldMerchandise) {
        this.soldMerchandise = soldMerchandise;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public void updateState(String state){
        this.deliveryState = state;
    }

    public Merchandise getSoldMerchandise() {
        return soldMerchandise;
    }


}
