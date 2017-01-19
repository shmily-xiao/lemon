package com.lemon.service;

import com.lemon.domain.impl.friend.FriendGroup;
import com.lemon.query.BaseQuery;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public interface IFriendGroupService extends IBaseService<FriendGroup,BaseQuery> {
    public List<FriendGroup> findFriendGroupByUserId(Long userId);

}
