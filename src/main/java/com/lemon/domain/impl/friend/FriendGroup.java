package com.lemon.domain.impl.friend;

import com.lemon.domain.impl.BaseDomain;
import com.lemon.domain.interfaces.friend.IFriendGroup;

/**
 * Created by simpletour_Jenkin on 2016/8/19.
 * 用户的分组表
 */
public class FriendGroup extends BaseDomain implements IFriendGroup{

    /**
     * 用户的id
     */
    private Long userId;

    /**
     * 用户的分组名字,会有一个默认分组 “我的好友”
     */
    private String groupName;

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
