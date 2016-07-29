package com.lemon.dao.impl;

import com.lemon.dao.IUserDao;
import com.lemon.domain.User;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Component;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
@Component
public class UserDaoImpl extends BaseDaoImpl<User,BaseQuery> implements IUserDao {


}
