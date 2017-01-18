package com.lemon.enums;

/**
 * Created by simpletour_Jenkin on 2017/1/18.
 */
public enum ContentThingsStatusEnum {
    WILLEXPIRE("即将到期"),
    DOING("正在进行"),
    FINISHED("已经完成"),
    FAILED("失败"),
    POSTPONE("延期");

    private String remark;

    ContentThingsStatusEnum(String remark) {
    this.remark =  remark;
    }

    public String getRemark() {
        return remark;
    }
}
