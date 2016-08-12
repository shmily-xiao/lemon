package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/3.
 * 标示用户的等级
 */
public enum UserType {
    NEWBIE("新手"),
    PROBATIONER("实习生");

    private String value;

    UserType(){}

    UserType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
