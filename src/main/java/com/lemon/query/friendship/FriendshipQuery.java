package com.lemon.query.friendship;

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
     * 朋友的类型，普通，特别关注
     */
    private String type;

    /**
     * 用户对好友的分类
     */
    private String group;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
