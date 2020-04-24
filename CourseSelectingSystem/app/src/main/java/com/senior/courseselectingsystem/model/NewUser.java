package com.senior.courseselectingsystem.model;

public class NewUser {
    private String num;
    private String name;
    private String pswd;
    private String identity;
    private int cls;
    private String contact;

    public NewUser(String num, String name, String pswd, String identity, int cls, String contact) {
        this.num = num;
        this.name = name;
        this.pswd = pswd;
        this.identity = identity;
        this.cls = cls;
        this.contact = contact;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getCls() {
        return cls;
    }

    public void setCls(int cls) {
        this.cls = cls;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
