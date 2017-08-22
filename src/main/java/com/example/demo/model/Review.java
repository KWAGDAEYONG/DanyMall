package com.example.demo.model;

import javax.persistence.*;

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
}
