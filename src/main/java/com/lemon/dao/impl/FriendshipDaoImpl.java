package com.lemon.dao.impl;

import com.lemon.dao.IFriendshipDao;
import com.lemon.domain.Friendship;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Component;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
@Component
public class FriendshipDaoImpl extends BaseDaoImpl<Friendship,BaseQuery> implements IFriendshipDao {
}
