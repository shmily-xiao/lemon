package com.lemon.domain.interfaces.friend;

import com.lemon.domain.interfaces.ibase.ICreatedTime;

/**
 * Created by simpletour_Jenkin on 2016/8/18.
 * 用户的分组表
 */
public interface IFriendGroup extends ICreatedTime{
    // 用户的id
    Long getUserId();
    void setUserId(Long userId);

    // 用户的分组名字
    String getGroupName();
    void setGroupName(String groupName);
}
