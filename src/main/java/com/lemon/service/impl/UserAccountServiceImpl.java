package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IUserAccountDao;
import com.lemon.domain.UserAccount;
import com.lemon.query.BaseQuery;
import com.lemon.service.IUserAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/8/12.
 */
@Service
public class UserAccountServiceImpl extends BaseServiceImpl<UserAccount, BaseQuery> implements IUserAccountService{

    @Resource
    private IUserAccountDao userAccountDao;

    @Override
    protected IBaseDao<UserAccount, BaseQuery> getBaseDao() {
        return this.userAccountDao;
    }
}
