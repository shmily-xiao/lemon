package com.lemon.domain.wechatUser;

import com.lemon.domain.BaseDomain;
import com.lemon.domain.interfaces.wechatUser.IWechatUser;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public class WechatUser extends BaseDomain implements IWechatUser{
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户微信的openId
     */
    private String openId;

    /**
     * 推荐人
     */
    private String referrer;

    /**
     * 推荐人的类型
     */
    private String referrerType;

    /**
     * 微信用户的状态
     */
    private String status;

    /**
     * 关联我们系统中的用户
     */
    private Long userId;

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getOpenId() {
        return openId;
    }

    @Override
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String getReferrer() {
        return referrer;
    }

    @Override
    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    @Override
    public String getReferrerType() {
        return referrerType;
    }

    @Override
    public void setReferrerType(String referrerType) {
        this.referrerType = referrerType;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
