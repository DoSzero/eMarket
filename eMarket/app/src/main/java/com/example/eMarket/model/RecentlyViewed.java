package com.example.eMarket.model;

public class RecentlyViewed {

    String name;
    String category;
    String description;
    String price;
    int imageUrl;


    public RecentlyViewed(String name, String category, String description, String price, int imageUrl) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageUrl() { return imageUrl; }

    public void setImageUrl(int image) { this.imageUrl = imageUrl; }

}

