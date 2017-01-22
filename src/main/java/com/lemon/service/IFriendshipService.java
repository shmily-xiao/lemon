package com.lemon.service;

import com.lemon.domain.impl.friend.Friendship;
import com.lemon.query.BaseQuery;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
public interface IFriendshipService extends IBaseService<Friendship,BaseQuery>{

    public List<Friendship> findFriendshipByUserId(Long userId);

    /**
     * 添加好友
     * 1.添加分组friendgroup
     * 2.添加朋友表friendship
     * 3.添加好友权限表accessControl
     * 4.更新friendship表中的accessControl的Id
     * @param currentUser
     * @param addUserId
     * @return
     */
    Boolean addFriend(Long currentUser,Long addUserId);
}
