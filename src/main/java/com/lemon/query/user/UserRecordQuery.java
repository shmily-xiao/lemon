package com.lemon.query.user;

import com.lemon.enums.UserType;
import com.lemon.query.BaseQuery;

/**
 * Created by simpletour_Jenkin on 2017/1/4.
 */
public class UserRecordQuery  extends BaseQuery{

    /**
     * 用户的等级
     */
    private String type;

    /**
     * 用户的成就值
     */
    private Long score;

    /**
     * 短信的条数
     */
    private Long smsCount;

    /**
     * 用户的id
     */
    private Long userId;

    /**
     * 用户对好友的公开策略
     */
    private Long accessControlId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(Long smsCount) {
        this.smsCount = smsCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccessControlId() {
        return accessControlId;
    }

    public void setAccessControlId(Long accessControlId) {
        this.accessControlId = accessControlId;
    }
}
