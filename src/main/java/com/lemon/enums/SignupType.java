package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public enum SignupType {
    MOBILE("手机号"),
    QQ("qq号");

    private String name;

    SignupType(){}

    SignupType(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
