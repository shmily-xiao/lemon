package com.lemon.domain;

import com.lemon.domain.interfaces.IFriendship;

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
    private String type;

    /**
     * 用户对好友的分类
     */
    private String group;

    @Override
    public Long getFriendId() {
        return friendId;
    }

    @Override
    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
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
    public String getGroup() {
        return group;
    }

    @Override
    public void setGroup(String group) {
        this.group = group;
    }
}
