package com.finalproject.starbucksordering.user.MenuWithCart;

import java.util.UUID;

public class Cart {
    private UUID mId;
    private UUID mDrinkId;
    private String mName;
    private Double mPrice;
    private Integer mNum;

    public Cart() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public UUID getDrinkId() {
        return mDrinkId;
    }

    public void setDrinkId(UUID drinkId) {
        mDrinkId = drinkId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
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
