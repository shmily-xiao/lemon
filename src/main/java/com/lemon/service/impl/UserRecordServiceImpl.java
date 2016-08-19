package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IUserRecordDao;
import com.lemon.domain.user.UserRecord;
import com.lemon.query.BaseQuery;
import com.lemon.service.IUserRecordService;
import org.jvnet.hk2.annotations.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
@Service
public class UserRecordServiceImpl extends BaseServiceImpl<UserRecord, BaseQuery> implements IUserRecordService {

    @Resource
    private IUserRecordDao userRecordDao;

    @Override
    protected IBaseDao<UserRecord, BaseQuery> getBaseDao() {
        return this.userRecordDao;
    }
}
