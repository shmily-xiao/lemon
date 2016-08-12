package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public enum SignupType {
    MOBILE("手机号"),
    QQ("qq号");

    private String value;

    SignupType(){}

    SignupType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
