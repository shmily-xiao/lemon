package com.lemon.query.wechatUser;

import com.lemon.query.BaseQuery;

/**
 * Created by simpletour_Jenkin on 2016/8/1.
 */
public class WechatUserQuery extends BaseQuery {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getReferrerType() {
        return referrerType;
    }

    public void setReferrerType(String referrerType) {
        this.referrerType = referrerType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
