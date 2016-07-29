package com.lemon.domain;

import java.sql.Timestamp;

/**
 * Created by simpletour_java on 2015/6/4.
 */
public class Cookies {
    // id
    private String id;
    // session
    private String sessionId;
    // 登录时间
    private Timestamp loginTime;
    // 存活时间
    private int lifeTime;

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
