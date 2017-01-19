package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IFriendshipDao;
import com.lemon.domain.impl.friend.Friendship;
import com.lemon.query.BaseQuery;
import com.lemon.query.friendship.FriendshipQuery;
import com.lemon.service.IFriendshipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<Friendship> findFriendshipByUserId(Long userId) {
        FriendshipQuery friendshipQuery = new FriendshipQuery();
        friendshipQuery.setUserId(userId);
        return  friendshipDao.findEntities(friendshipQuery);
    }
}
