package com.example.class5;

import java.io.Serializable;

public class Dish implements Serializable{
    private String mName;   //菜名
    public static final int CHINESE_FOOD=1;
    public static final int FAST_FOOD=2;
    public static final int DESSERT_FOOD=3;
    private int mImgResid;  //菜品图片
    private int mPrice; //菜品价格
    private int mType;  //菜品类别
    private String mInfo;   //介绍

    public Dish(String name, int imgResid, int price, int type, String info) {
        mName = name;
        mImgResid = imgResid;
        mPrice = price;
        mType = type;
        mInfo = info;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getImgResid() {
        return mImgResid;
    }

    public void setImgResid(int imgResid) {
        mImgResid = imgResid;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }
}
