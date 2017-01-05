package com.lemon.enums;

import com.lemon.framework.enumwrapper.Options;

/**
 * Created by simpletour_Jenkin on 2016/8/3.
 * 用户对自己事件的公开策略
 */
public enum StrategyType implements Options{
    PUBLIC("公开"),
    PRIVATE("私有"),
    FRIENDSHIP("对好友可见");

    private String remark;

    StrategyType(){}

    StrategyType(String remark){
        this.remark = remark;
    }


    @Override
    public String getName() {
        return remark;
    }

    public String getValue() {
        return this.name();
    }

    public String getRemark() {
        return remark;
    }
}
