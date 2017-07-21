package com.lemon.form.user;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by wangzaijun on 2017/7/21.
 */
public class UserSendAuthCodeEmailForm {
    @NotEmpty
    @NotNull
    // 手机号
    private String account;

    @NotNull
    @NotEmpty
    private String authKey;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
