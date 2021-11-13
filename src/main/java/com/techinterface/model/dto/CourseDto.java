package com.techinterface.model.dto;

public class CourseDto {
    private Integer id;
    private String title;
    private int leturesCount;
    private int hourCount;
    private float price;
    private float promotionPrice;
    private String content;

    public CourseDto() {
    }

    public CourseDto(Integer id, String title, int leturesCount, int hourCount, float price, float promotionPrice,
            String content) {
        this.id = id;
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

}
