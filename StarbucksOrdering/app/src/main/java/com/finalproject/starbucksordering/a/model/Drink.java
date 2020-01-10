package com.finalproject.starbucksordering.a.model;

import java.util.UUID;

public class Drink {
    private UUID mId;
    private String mName;
    private String mType;
    private boolean mHot;
    private Double mPrice;
    private String mDetail;
    private String mImage;

    public Drink() {
        mId = UUID.randomUUID();
        mHot = false;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public boolean isHot() {
        return mHot;
    }

    public void setHot(boolean hot) {
        mHot = hot;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        mPrice = price;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
