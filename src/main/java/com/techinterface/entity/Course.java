package com.techinterface.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String title;

    @Column(name = "letures_count")
    private int leturesCount;

    @Column(name = "hour_count")
    private int hourCount;

    @Column(name = "price")
    private float price;

    @Column(name = "promotion_price")
    private float promotionPrice;
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Course() {
    }

    public Course(String title, int leturesCount, int hourCount, float price, float promotionPrice, String content) {

        this.title = title;
        this.leturesCount = leturesCount;
        this.hourCount = hourCount;
        this.price = price;
        this.promotionPrice = promotionPrice;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLeturesCount() {
        return leturesCount;
    }

    public void setLeturesCount(int leturesCount) {
        this.leturesCount = leturesCount;
    }

    public int getHourCount() {
        return hourCount;
    }

    public void setHourCount(int hourCount) {
        this.hourCount = hourCount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(float promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
