package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IFriendshipDao;
import com.lemon.domain.Friendship;
import com.lemon.query.BaseQuery;
import com.lemon.service.IFriendshipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
@Service
public class FriendshipServiceImpl  extends BaseServiceImpl<Friendship,BaseQuery> implements IFriendshipService{

    @Resource
    private IFriendshipDao friendshipDao;

    @Override
    protected IBaseDao<Friendship, BaseQuery> getBaseDao() {
        return friendshipDao;
    }
}
