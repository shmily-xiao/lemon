package com.lemon.query.access;

import com.lemon.enums.StrategyType;
import com.lemon.query.BaseQuery;

/**
 * Created by simpletour_Jenkin on 2017/1/16.
 */
public class AccessControlQuery extends BaseQuery {
    /**
     * 外键的id
     */
    private Long rowId;

    /**
     * 外键的表 content 和 user
     */
    private String rowTable;

    /**
     * 策略，对于内容和user来说，内容应该是不一样的
     */
    private StrategyType strategy;

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public String getRowTable() {
        return rowTable;
    }

    public void setRowTable(String rowTable) {
        this.rowTable = rowTable;
    }

    public StrategyType getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyType strategy) {
        this.strategy = strategy;
    }
}
