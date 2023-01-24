package com.example.blum;

import com.example.blum.model.ProductNew;

public class Products {
    private Integer id;
    private Integer category;
    private String title;
    private String imageURL;
    private String articleNumber;
    private String distance;
    private String height;
    private String colour;
    private Integer articleID;
    private String fixingMethod;
    private String productURL;
    private Integer parentID;
    private String doorType;
    private String openingAngle;
    private String productSystem;
    private String cabinetHeight;
    private String cabinetMinDepth;
    private String powerFactorLF;

    public Products(ProductNew p){
        this.id = p.getId();
        this.category = p.getCategory();
        this.title = p.getTitle();
        this.imageURL = p.getImageURL();
        this.articleNumber = p.getProductInfo().get(0).getArticleNumber();
        this.distance = String.valueOf(p.getProductInfo().get(0).getDistance());
        this.height = String.valueOf(p.getProductInfo().get(0).getHeight());
        this.colour = p.getProductInfo().get(0).getColour();
        this.articleID = p.getProductInfo().get(0).getParentID();
        this.fixingMethod = p.getProductInfo().get(0).getFixingMethod();
        this.productURL = p.getProductInfo().get(0).getProductURL();
        this.parentID = p.getProductInfo().get(0).getParentID();
        this.doorType = p.getProductInfo().get(0).getDoorType();
        this.openingAngle = p.getProductInfo().get(0).getOpeningAngle();
        this.productSystem = p.getProductInfo().get(0).getProductSystem();
        this.cabinetHeight = p.getProductInfo().get(0).getCabinetHeight();
        this.cabinetMinDepth =p.getProductInfo().get(0).getCabinetMinDepth();
        this.powerFactorLF = p.getProductInfo().get(0).getPowerFactorLF();

    }


    public String getCabinetHeight() {
        return cabinetHeight;
    }

    public void setCabinetHeight(String cabinetHeight) {
        this.cabinetHeight = cabinetHeight;
    }

    public String getCabinetMinDepth() {
        return cabinetMinDepth;
    }

    public void setCabinetMinDepth(String cabinetMinDepth) {
        this.cabinetMinDepth = cabinetMinDepth;
    }

    public String getPowerFactorLF() {
        return powerFactorLF;
    }

    public void setPowerFactorLF(String powerFactorLF) {
        this.powerFactorLF = powerFactorLF;
    }

    public String getDoorType() {
        return doorType;
    }

    public void setDoorType(String doorType) {
        this.doorType = doorType;
    }

    public String getOpeningAngle() {
        return openingAngle;
    }

    public void setOpeningAngle(String openingAngle) {
        this.openingAngle = openingAngle;
    }

    public String getProductSystem() {
        return productSystem;
    }

    public void setProductSystem(String productSystem) {
        this.productSystem = productSystem;
    }

    public String getFixingMethod() {
        return fixingMethod;
    }

    public void setFixingMethod(String fixingMethod) {
        this.fixingMethod = fixingMethod;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }


    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getArticleID() {
        return articleID;
    }

    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public Integer getParentID() {
        return parentID;
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

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
