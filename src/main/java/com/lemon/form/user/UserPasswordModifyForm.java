package com.lemon.form.user;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class UserPasswordModifyForm {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
