package com.demo.domain.itfc.ibase;

import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 *
 * 属性 createdTime, id
 */
public interface ICreatedTime extends IId{
    // 创建时间
    Date getCreatedTime();
    void setCreatedTime(Date createdTime);
}
