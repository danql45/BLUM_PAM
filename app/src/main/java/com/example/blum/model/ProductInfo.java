package com.example.blum.model;

public class ProductInfo {

    private int parentID;
    private String articleNumber;
    private String productURL;
    private Double distance;
    private Double height;
    private String colour;
    private String fixingMethod;
    private String openingAngle;
    private String productSystem;
    private String cabinetHeight;
    private String cabinetMinDepth;
    private String powerFactorLF;
    private String doorType;


    public ProductInfo() {
    }

    public String getDoorType() {
        return doorType;
    }

    public void setDoorType(String doorType) {
        this.doorType = doorType;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFixingMethod() {
        return fixingMethod;
    }

    public void setFixingMethod(String fixingMethod) {
        this.fixingMethod = fixingMethod;
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
}
