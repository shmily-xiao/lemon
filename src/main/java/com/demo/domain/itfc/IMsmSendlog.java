package com.demo.domain.itfc;

import com.demo.domain.itfc.ibase.ICreatedTime;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public interface IMsmSendlog extends ICreatedTime{
    // 验证码
    String getAuthCode();
    void setAuthCode(String authCode);

    // 手机号码
    String getMobile();
    void setMobile(String mobile);

    // 理由，应该是如果短信发送不成功，第三方平台给出的理由
    String getReason();
    void setReason(String reason);

    // 用来标示是否发送成功
    String getStatus();
    void setStatus(String status);

}
