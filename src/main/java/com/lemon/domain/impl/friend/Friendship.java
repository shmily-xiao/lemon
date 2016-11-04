package com.lemon.domain.impl.friend;

import com.lemon.domain.impl.BaseDomain;
import com.lemon.domain.interfaces.friend.IFriendship;
import com.lemon.enums.FriendType;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 * 关系实体，多对多的关系
 */
public class Friendship extends BaseDomain implements IFriendship{
    /**
     * 用户的id
     */
    private Long userId;

    /**
     * 朋友的id
     */
    private Long friendId;

    /**
     * 朋友的类型，普通，特别关注
     */
    private FriendType type;

    /**
     * 用户对好友的分组
     */
    private Long friendGroupId;

    /**
     * 用户这条信息对好友的公开策略
     */
    private Long accessControlId;

    @Override
    public Long getFriendId() {
        return friendId;
    }

    @Override
    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    @Override
    public FriendType getType() {
        return type;
    }

    @Override
    public void setType(FriendType type) {
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

    @Override
    public Long getAccessControlId() {
        return accessControlId;
    }

    @Override
    public void setAccessControlId(Long accessControlId) {
        this.accessControlId = accessControlId;
    }

    @Override
    public Long getFriendGroupId() {
        return friendGroupId;
    }

    @Override
    public void setFriendGroupId(Long friendGroupId) {
        this.friendGroupId = friendGroupId;
    }
}
