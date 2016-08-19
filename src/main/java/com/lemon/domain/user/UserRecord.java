package com.lemon.domain.user;

import com.lemon.domain.BaseDomain;
import com.lemon.domain.interfaces.user.IUserRecord;
import com.lemon.enums.UserType;

/**
 * Created by simpletour_Jenkin on 2016/8/19.
 * 用户的业务数据表
 */
public class UserRecord extends BaseDomain implements IUserRecord {
    /**
     * 用户的等级
     */
    private UserType type;

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


    @Override
    public Long getAccessControlId() {
        return accessControlId;
    }

    @Override
    public void setAccessControlId(Long accessControlId) {
        this.accessControlId = accessControlId;
    }

    @Override
    public Long getScore() {
        return score;
    }

    @Override
    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public Long getSmsCount() {
        return smsCount;
    }

    @Override
    public void setSmsCount(Long smsCount) {
        this.smsCount = smsCount;
    }

    @Override
    public UserType getType() {
        return type;
    }

    @Override
    public void setType(UserType type) {
        this.type = type;
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
