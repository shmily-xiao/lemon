package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IWechatUserDao;
import com.lemon.domain.impl.wechatUser.WechatUser;
import com.lemon.query.BaseQuery;
import com.lemon.service.IWechatUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
@Service
public class WechatUserServiceImpl extends BaseServiceImpl<WechatUser,BaseQuery> implements IWechatUserService{

    @Resource
    private IWechatUserDao wechatUserDao;

    @Override
    protected IBaseDao<WechatUser, BaseQuery> getBaseDao() {
        return wechatUserDao;
    }
}
