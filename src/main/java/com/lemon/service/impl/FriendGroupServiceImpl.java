package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IFriendGroupDao;
import com.lemon.domain.impl.friend.FriendGroup;
import com.lemon.query.BaseQuery;
import com.lemon.service.IFriendGroupService;
import org.jvnet.hk2.annotations.Service;

import javax.annotation.Resource;

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
}
