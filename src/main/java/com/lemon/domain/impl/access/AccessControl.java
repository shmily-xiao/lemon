package com.lemon.domain.impl.access;

import com.lemon.domain.impl.BaseDomain;
import com.lemon.domain.interfaces.access.IAccessControl;
import com.lemon.enums.StrategyType;

/**
 * Created by simpletour_Jenkin on 2016/8/22.
 * 权限的控制表
 */
public class AccessControl extends BaseDomain implements IAccessControl {
    /**
     * 外键的id
     */
    private Long rowId;

    /**
     * 外键的表"content" ,"userRecord","friendship"
     *
     * 权限的范围从上至下依次扩大：
     * content ：的策略是对单个内容而言的，拥有最小内容的管理权限
     * friendship：的策略是针对好友的，比如某些内容屏蔽某一位好友
     * userRecord:  的策略是对用户整个内容而言的，如果设为私有，相当于空间被锁定，除了自己任何人不能看
     *
     */
    private String rowTable;

    /**
     * 策略，对于内容和user来说，内容应该是不一样的
     */
    private StrategyType strategy;

    public AccessControl() {
    }

    public AccessControl(Long rowId, String rowTable, StrategyType strategy) {
        this.rowId = rowId;
        this.rowTable = rowTable;
        this.strategy = strategy;
    }

    @Override
    public Long getRowId() {
        return rowId;
    }

    @Override
    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    @Override
    public String getRowTable() {
        return rowTable;
    }

    @Override
    public void setRowTable(String rowTable) {
        this.rowTable = rowTable;
    }

    @Override
    public StrategyType getStrategy() {
        return strategy;
    }

    @Override
    public void setStrategy(StrategyType strategy) {
        this.strategy = strategy;
    }
}
