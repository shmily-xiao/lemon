package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2016/8/12.
 */
public enum AccountType {
    MOBILE("手机号"),
    QQ("qq号"),
    SYSTEM_DEFAULT("系统分配");

    private String value;

    AccountType (){}

    AccountType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
