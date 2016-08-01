package com.lemon.query.msmSendlog;

import com.lemon.query.BaseQuery;

/**
 * Created by simpletour_Jenkin on 2016/8/1.
 */
public class MsmSendlogQuery extends BaseQuery {
    /**
     * 验证码
     */
    private String authCode;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 如果失败了的理由
     */
    private String reason;

    /**
     * 发送短信的状态
     */
    private String status;

    public MsmSendlogQuery(){}

    public MsmSendlogQuery(String mobile){
        this.mobile = mobile;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
