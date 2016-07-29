package com.demo.service.impl;

import com.demo.dao.IBaseDao;
import com.demo.dao.IMsmSendlogDao;
import com.demo.domain.MsmSendlog;
import com.demo.query.BaseQuery;
import com.demo.service.IMsmSendlogService;
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
