package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/3.
 * 标示用户的等级
 */
public enum UserType {
    NEWBIE("新手"),
    PROBATIONER("实习生");

    private String name;

    UserType(){}

    UserType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
