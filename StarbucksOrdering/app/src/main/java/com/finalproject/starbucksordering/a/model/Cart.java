package com.finalproject.starbucksordering.a.model;

import java.util.UUID;

public class Cart {
    private UUID mId;
    private String mDrinkName;
    private String mUsername;
    private Double mPrice;
    private Integer mNum;

    public Cart() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getDrinkName() {
        return mDrinkName;
    }

    public void setDrinkName(String drinkName) {
        mDrinkName = drinkName;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        mPrice = price;
    }

    public Integer getNum() {
        return mNum;
    }

    public void setNum(Integer num) {
        mNum = num;
    }
}
