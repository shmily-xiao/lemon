package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IMsmSendlogDao;
import com.lemon.domain.MsmSendlog;
import com.lemon.query.BaseQuery;
import com.lemon.service.IMsmSendlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
@Service
public class MsmSendlogServiceImpl extends BaseServiceImpl<MsmSendlog,BaseQuery> implements IMsmSendlogService {

    @Resource
    private IMsmSendlogDao msmSendlogDao;

    @Override
    protected IBaseDao<MsmSendlog, BaseQuery> getBaseDao() {
        return msmSendlogDao;
    }
}
