package com.lemon.domain.interfaces.access;

import com.lemon.domain.interfaces.ibase.ICreatedTime;
import com.lemon.enums.StrategyType;

/**
 * Created by simpletour_Jenkin on 2016/8/18.
 * 权限控制表，关系表
 */
public interface IAccessControl extends ICreatedTime {
    // 外键关系表
    Long getRowId();
    void setRowId(Long rowId);

    // 外键表
    String getRowTable();
    void setRowTable(String rowTable);

    // 策略，对于内容和user来说，内容应该是不一样的
    StrategyType getStrategy();
    void setStrategy(StrategyType strategy);

}
