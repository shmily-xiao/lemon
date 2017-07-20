package com.lemon.domain.impl.msm;

import com.lemon.domain.impl.BaseDomain;
import com.lemon.domain.interfaces.msm.IMsmSendlog;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public class MsmSendlog extends BaseDomain implements IMsmSendlog {

    /**
     * 验证码
     */
    private String authCode;

    /**
     * 手机号 / 邮箱
     */
    private String mobile;

    /**
     * 如果失败了的理由
     */
    private String reason;

    /**
     * 发送短信的状态  成功（SUCCESS）/失败（FAILED）
     */
    private String status;

    @Override
    public String getAuthCode() {
        return authCode;
    }

    @Override
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}
