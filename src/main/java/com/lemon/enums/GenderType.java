package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public enum GenderType {
    Male("男"),
    Female("女");

    private String value;

    GenderType(){}

    GenderType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
