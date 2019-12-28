package com.example.class2;

import android.widget.ImageView;
import android.widget.TextView;


public class Image {
    private int mImg;
    private int mImgName;

    public Image(int img, int imgName) {
        mImg = img;
        mImgName = imgName;
    }

    public int getImg() {
        return mImg;
    }

    public void setImg(int img) {
        mImg = img;
    }

    public int getImgName() {
        return mImgName;
    }

    public void setImgName(int imgName) {
        mImgName = imgName;
    }
}
