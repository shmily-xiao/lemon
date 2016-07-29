package com.demo.service.impl;

import com.demo.dao.IBaseDao;
import com.demo.dao.IWechatUserDao;
import com.demo.domain.WechatUser;
import com.demo.query.BaseQuery;
import com.demo.service.IWechatUserService;
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
