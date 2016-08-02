package com.lemon.form.user;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class UserAccountForm {

    /**
     * 注册的时候使的手机号，也是以后用户登录的账号
     */
    @NotBlank
    @NotNull
    private String mobile;

    /**
     * 用户的密码，应该是hash之后的
     */
    @NotBlank
    @NotNull
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
