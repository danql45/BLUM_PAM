package com.example.blum.model;

import java.util.List;

public class ProductNew {

    private int id;
    private int category;
    private String title;
    private String imageURL;
    private List<ProductInfo> productInfo;

    public ProductNew() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<ProductInfo> getProductInfo() {
        return productInfo;
    }

    public void setProductInfoList(List<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }
}
