package com.demo.service.impl;

import com.demo.dao.IBaseDao;
import com.demo.dao.IUserDao;
import com.demo.domain.User;
import com.demo.query.BaseQuery;
import com.demo.service.IUserService;
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
