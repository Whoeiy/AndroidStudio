package com.senior.courseselectingsystem.model;

import java.io.Serializable;

public class User implements Serializable {
    private String num;
    private String identity;

    public User(String num, String identity) {
        this.num = num;
        this.identity = identity;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
