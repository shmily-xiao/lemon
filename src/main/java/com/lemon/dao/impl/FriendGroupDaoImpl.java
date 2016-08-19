package com.lemon.dao.impl;

import com.lemon.dao.IFriendGroupDao;
import com.lemon.domain.friend.FriendGroup;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
@Repository
public class FriendGroupDaoImpl extends BaseDaoImpl<FriendGroup, BaseQuery> implements IFriendGroupDao {
}
