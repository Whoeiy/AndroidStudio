package com.example.class9;

public class Demo {
    private String mSid;
    private String mSname;
    private String mSage;
    private String mSclass;

    public Demo(String sid, String sname, String sage, String sclass) {
        mSid = sid;
        mSname = sname;
        mSage = sage;
        mSclass = sclass;
    }

    public String getSid() {
        return mSid;
    }

    public void setSid(String sid) {
        mSid = sid;
    }

    public String getSname() {
        return mSname;
    }

    public void setSname(String sname) {
        mSname = sname;
    }

    public String getSage() {
        return mSage;
    }

    public void setSage(String sage) {
        mSage = sage;
    }

    public String getSclass() {
        return mSclass;
    }

    public void setSclass(String sclass) {
        mSclass = sclass;
    }
}
