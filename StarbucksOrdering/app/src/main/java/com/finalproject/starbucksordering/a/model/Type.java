package com.finalproject.starbucksordering.a.model;

import java.util.UUID;

public class Type {
    private UUID mUUID;
    private String mType;

    public Type() { mUUID = UUID.randomUUID(); }

    public Type(UUID uuid){
        mUUID = uuid;
    }

    public UUID getId() {
        return mUUID;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }
}


