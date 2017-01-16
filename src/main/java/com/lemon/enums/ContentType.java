package com.lemon.enums;

import com.lemon.framework.enumwrapper.Options;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public enum ContentType implements Options{
    DREAM("想做的事",10L),
    TRIFLES("琐碎的事",2L),
    LYRICISM("抒情的话",5L);

    private String remark;

    private Long score;

    ContentType(){}

    ContentType(String remark){
        this.remark = remark;
    }

    ContentType(String remark, Long score) {
        this.remark = remark;
        this.score = score;
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

    public Long getScore() {
        return score;
    }
}
