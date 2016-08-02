package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IUserDao;
import com.lemon.domain.User;
import com.lemon.query.BaseQuery;
import com.lemon.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User,BaseQuery> implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    protected IBaseDao<User, BaseQuery> getBaseDao() {
        return userDao;
    }


}
