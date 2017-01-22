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
