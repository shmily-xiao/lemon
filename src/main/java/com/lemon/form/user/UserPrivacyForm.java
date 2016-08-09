package com.lemon.form.user;

import com.lemon.enums.ZoneStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public class UserPrivacyForm {

    /**
     * 设置是否公开自己的空间
     */
    @NotBlank
    @NotNull
    private ZoneStatus zoneStatus;


    public ZoneStatus getZoneStatus() {
        return zoneStatus;
    }

    public void setZoneStatus(ZoneStatus zoneStatus) {
        this.zoneStatus = zoneStatus;
    }
}
