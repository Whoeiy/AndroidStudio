package com.finalproject.starbucksordering.a.model;

import java.util.UUID;

public class User {
    private String mUsername;
    private String mName;
    private String mPassword;
    private String mGender;
    private String mYear;
    private String mMonth;
    private String mPhone;

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getMonth() {
        return mMonth;
    }

    public void setMonth(String month) {
        mMonth = month;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }
}
