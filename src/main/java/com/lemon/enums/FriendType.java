package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/18.
 */
public enum FriendType {
    GENERAL("普通"),
    SPECIAL("特殊"),
    HATE("不喜欢");

    private String value;

    FriendType(){}

    FriendType(String value){
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
