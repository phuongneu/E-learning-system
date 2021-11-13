package com.techinterface.model.request;

public class AddCourseDto {

    private String title;
    private int leturesCount;
    private int hourCount;

    private float price;
    private float promotionPrice;

    private String content;

    private int categoryId;

    public AddCourseDto() {
    }

    public AddCourseDto(String title, int leturesCount, int hourCount, float price, float promotionPrice,
            String content, int categoryId) {
        this.title = title;
        this.leturesCount = leturesCount;
        this.hourCount = hourCount;
        this.price = price;
        this.promotionPrice = promotionPrice;
        this.content = content;
        this.categoryId = categoryId;
    }

    public int getHourCount() {
        return hourCount;
    }

    public void setHourCount(int hourCount) {
        this.hourCount = hourCount;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
