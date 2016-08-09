package com.lemon.domain.interfaces.ibase;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 *
 * 属性 createdTime, id
 */
public interface ICreatedTime extends IId{
    // 创建时间
    LocalDateTime getCreatedTime();
    void setCreatedTime(LocalDateTime createdTime);
}
