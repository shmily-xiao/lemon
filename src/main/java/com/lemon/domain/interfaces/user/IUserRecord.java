package com.lemon.domain.interfaces.user;

import com.lemon.domain.interfaces.ibase.ICreatedTime;
import com.lemon.enums.UserType;

/**
 * Created by simpletour_Jenkin on 2016/8/18.
 *
 * 用户的业务数据表
 */
public interface IUserRecord extends ICreatedTime {
    //用户的类型, 用于表示用户的等级
    UserType getType();
    void setType(UserType type);

    // 用户的得分，或者是叫做 成就，每完成一件自己想做的事情就加分，理论上没有上线
    // 但是以后可以通过成就值给用户划分等级
    Long getScore();
    void setScore(Long score);

    // 短信的条数
    Long getSmsCount();
    void setSmsCount(Long smsCount);

    // 用户的id
    Long getUserId();
    void setUserId(Long userId);

    // 用户对好友的公开策略
    Long getAccessControlId();
    void setAccessControlId(Long accessControlId);

}
