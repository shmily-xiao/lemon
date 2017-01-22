package com.lemon.service.impl;

import com.lemon.dao.IAccessControlDao;
import com.lemon.dao.IBaseDao;
import com.lemon.dao.IFriendGroupDao;
import com.lemon.dao.IFriendshipDao;
import com.lemon.domain.impl.access.AccessControl;
import com.lemon.domain.impl.friend.FriendGroup;
import com.lemon.domain.impl.friend.Friendship;
import com.lemon.enums.FriendType;
import com.lemon.enums.StrategyType;
import com.lemon.query.BaseQuery;
import com.lemon.query.friendship.FriendshipQuery;
import com.lemon.service.IFriendshipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
@Service
public class FriendshipServiceImpl  extends BaseServiceImpl<Friendship,BaseQuery> implements IFriendshipService{

    @Resource
    private IFriendshipDao friendshipDao;

    @Resource
    private IFriendGroupDao friendGroupDao;

    @Resource
    private IAccessControlDao accessControlDao;

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

    @Override
    @Transactional
    public Boolean addFriend(Long currentUser, Long addUserId) {

        // 插入分组
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setGroupName("我的好友");
        friendGroup.setUserId(currentUser);
        friendGroupDao.insert(friendGroup);

        // 插入朋友的记录，但是少了策略的id
        Friendship friendship = new Friendship();
        friendship.setUserId(currentUser);
        friendship.setFriendId(addUserId);
        friendship.setType(FriendType.GENERAL);
        friendship.setFriendGroupId(friendGroup.getId());
        friendshipDao.insert(friendship);

        // 插入策略的，就是当前用户对好友的公开策略
        AccessControl accessControl = new AccessControl();
        accessControl.setRowId(friendship.getId());
        accessControl.setRowTable("friendship");
        accessControl.setStrategy(StrategyType.PUBLIC);
        accessControlDao.insert(accessControl);

        // 更新friendship中的策略id，是数据完整
        friendship.setAccessControlId(accessControl.getId());
        friendshipDao.update(friendship);

        return Boolean.TRUE;
    }
}
