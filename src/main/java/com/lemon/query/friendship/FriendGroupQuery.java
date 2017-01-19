package com.lemon.query.friendship;

import com.lemon.query.BaseQuery;

/**
 * Created by simpletour_Jenkin on 2017/1/19.
 */
public class FriendGroupQuery extends BaseQuery{
    /**
     * 用户的id
     */
    private Long userId;

    /**
     * 用户的分组名字
     */
    private String groupName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGroupName() {

        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
