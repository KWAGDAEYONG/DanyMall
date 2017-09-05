package com.example.demo.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "IS_STOP_SALE='FALSE'")
public class Merchandise implements Comparable<Merchandise> {

    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private String color;
    private String size;
    private int amount;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_merchandise_item"))
    private Item item;

    @OneToOne(mappedBy = "soldMerchandise")
    private Sold sold;

    private int salesVolume;

    @Column(name = "isStopSale")
    private boolean isStopSale=false;

    public void setStopSale(boolean stopSale) {
        isStopSale = stopSale;
    }

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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public Long getId() {
        return id;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public Item getItem() {
        return item;
    }

    public void updateAmount(int amount){
        this.amount = amount;
    }

    public boolean release(){
        if(this.amount==0){
            return false;
        }
        this.amount = this.amount-1;
        return true;
    }

    public int getAmount() {
        return amount;
    }

    public void updateSalesVolume(){
        this.salesVolume++;
    }

    @Override
    public int compareTo(Merchandise merchandise){
        if(this.salesVolume>merchandise.salesVolume){
            return -1;
        }else{
            return 1;
        }
    }

}
