package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/3.
 * 用户对自己事件的公开策略
 */
public enum ZoneStatus {
    PUBLIC("公开"),
    PRIVATE("私有"),
    FRIENDSHIP("对好友可见");

    private String value;

    ZoneStatus(){}

    ZoneStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
