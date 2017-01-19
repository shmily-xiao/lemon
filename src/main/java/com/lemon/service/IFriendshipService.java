package com.lemon.service;

import com.lemon.domain.impl.friend.Friendship;
import com.lemon.query.BaseQuery;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
public interface IFriendshipService extends IBaseService<Friendship,BaseQuery>{
    public List<Friendship> findFriendshipByUserId(Long userId);
}
