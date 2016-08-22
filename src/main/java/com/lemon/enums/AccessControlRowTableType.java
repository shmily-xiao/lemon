package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/22.
 */
public enum AccessControlRowTableType {
    USER("用户"),
    CONTENT("内容");

    private String value;

    AccessControlRowTableType() {
    }

    AccessControlRowTableType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
