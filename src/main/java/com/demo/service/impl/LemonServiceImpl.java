package com.demo.service.impl;

import com.demo.dao.IBaseDao;
import com.demo.dao.ILemonDao;
import com.demo.domain.Lemon;
import com.demo.query.BaseQuery;
import com.demo.service.ILemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
@Service
public class LemonServiceImpl extends BaseServiceImpl<Lemon,BaseQuery> implements ILemonService{
    @Resource
    private ILemonDao lemonDao;

    @Override
    protected IBaseDao<Lemon, BaseQuery> getBaseDao() {
        return lemonDao;
    }
}
