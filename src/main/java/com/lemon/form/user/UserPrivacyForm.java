package com.lemon.form.user;

import com.lemon.enums.StrategyType;

import javax.validation.constraints.NotNull;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public class UserPrivacyForm {

    /**
     * 设置是否公开自己的空间
     */
    @NotNull
    private StrategyType zoneStatus;


    public StrategyType getZoneStatus() {
        return zoneStatus;
    }

    public void setZoneStatus(StrategyType zoneStatus) {
        this.zoneStatus = zoneStatus;
    }
}
