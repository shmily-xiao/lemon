package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/3.
 * 用户对自己事件的公开策略
 */
public enum ZoneStatus {
    PUBLIC("公开"),
    PRIVATE("私有"),
    FRIENDSHIP("对好友可见");

    private String name;

    ZoneStatus(){}

    ZoneStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
