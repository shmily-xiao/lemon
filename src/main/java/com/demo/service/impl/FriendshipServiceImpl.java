package com.demo.service.impl;

import com.demo.dao.IBaseDao;
import com.demo.dao.IFriendshipDao;
import com.demo.domain.Friendship;
import com.demo.query.BaseQuery;
import com.demo.service.IFriendshipService;
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
