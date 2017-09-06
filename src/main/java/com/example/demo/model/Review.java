package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_user"))
    private User writer;

    private String content;

    private int score;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_item"))
    private Item item;

    @Transient
    private List<Integer> stars = new ArrayList<Integer>();

    @Transient
    private List<Integer> noStars = new ArrayList<Integer>();

    public void setStar(){
        for(int i = 0; i<this.score; i++){
            this.stars.add(1);
        }
        for(int i = 0; i<5-this.score; i++){
            this.noStars.add(1);
        }
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public int getScore() {
        return score;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", item=" + item +
                '}';
    }
}
