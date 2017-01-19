package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IFriendGroupDao;
import com.lemon.domain.impl.friend.FriendGroup;
import com.lemon.query.BaseQuery;
import com.lemon.query.friendship.FriendGroupQuery;
import com.lemon.service.IFriendGroupService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
@Service
public class FriendGroupServiceImpl extends BaseServiceImpl<FriendGroup, BaseQuery> implements IFriendGroupService{
    @Resource
    private IFriendGroupDao friendGroupDao;

    @Override
    protected IBaseDao<FriendGroup, BaseQuery> getBaseDao() {
        return this.friendGroupDao;
    }

    @Override
    public List<FriendGroup> findFriendGroupByUserId(Long userId) {

        FriendGroupQuery friendGroupQuery = new FriendGroupQuery();
        friendGroupQuery.setUserId(userId);
        return friendGroupDao.findEntities(friendGroupQuery);
    }
}
