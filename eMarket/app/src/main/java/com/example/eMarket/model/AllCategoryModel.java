package com.example.eMarket.model;

public class AllCategoryModel {


    Integer id;
    Integer imageurl;
    String text;

    public AllCategoryModel(Integer id, Integer imageurl, String text) {
        this.id = id;
        this.imageurl = imageurl;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImageurl() {
        return imageurl;
    }

    public void setImageurl(Integer imageurl) {
        this.imageurl = imageurl;
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }
}
