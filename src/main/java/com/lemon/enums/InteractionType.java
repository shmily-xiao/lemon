package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/18.
 */
public enum  InteractionType {
    COLLECT("收藏"),
    LIKE("点赞");

    private String value;

    InteractionType(){}

    InteractionType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
