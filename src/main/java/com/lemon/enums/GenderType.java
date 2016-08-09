package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public enum GenderType {
    Male("男"),
    Female("女");

    private String name;

    GenderType(){}

    GenderType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
