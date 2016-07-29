package com.lemon.domain.interfaces.ibase;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 *
 * 属性 name , id , createdTime
 */
public interface IName extends ICreatedTime{
    // 名字
    String getName();
    void setName(String name);
}
