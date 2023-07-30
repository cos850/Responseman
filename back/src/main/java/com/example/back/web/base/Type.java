package com.example.back.web.base;

public enum Type {
    SOCKET("S"), HTTP("H");

    String type;

    Type(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
