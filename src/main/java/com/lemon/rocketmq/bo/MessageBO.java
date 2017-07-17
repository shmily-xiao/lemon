package com.lemon.rocketmq.bo;

/**
 * Created by wangzaijun on 2017/7/17.
 */
public class MessageBO {
    private String email;
    private String theme;

    public MessageBO() {
    }

    public MessageBO(String email, String theme) {
        this.email = email;
        this.theme = theme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
