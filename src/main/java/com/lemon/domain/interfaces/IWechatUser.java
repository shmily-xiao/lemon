package com.lemon.domain.interfaces;

import com.lemon.domain.interfaces.ibase.ICreatedTime;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public interface IWechatUser extends ICreatedTime{
    // 手机号码
    String getMobile();
    void setMobile(String mobile);

    // 用户的openId
    String getOpenId();
    void setOpenId(String openId);

    // 推荐人
    String getReferrer();
    void setReferrer(String referrer);

    // 推荐人的类型
    String getReferrerType();
    void setReferrerType(String referrerType);

    // wechat用户的状态
    String getStatus();
    void setStatus(String status);

    // 关联我们系统中的用户id
    Long getUserId();
    void setUserId(Long userId);

}
