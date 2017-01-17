package com.lemon.query.friendship;

import com.lemon.enums.FriendType;
import com.lemon.query.BaseQuery;

/**
 * Created by simpletour_Jenkin on 2016/8/1.
 */
public class FriendshipQuery extends BaseQuery {
    /**
     * 用户的id
     */
    private Long userId;

    /**
     * 朋友的id
     */
    private Long friendId;

    /**
     * 用户对好友的分类
     */
    private String group;

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


    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public FriendType getType() {
        return type;
    }

    public void setType(FriendType type) {
        this.type = type;
    }

    public Long getFriendGroupId() {
        return friendGroupId;
    }

    public void setFriendGroupId(Long friendGroupId) {
        this.friendGroupId = friendGroupId;
    }

    public Long getAccessControlId() {
        return accessControlId;
    }

    public void setAccessControlId(Long accessControlId) {
        this.accessControlId = accessControlId;
    }
}
