package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.ILemonDao;
import com.lemon.domain.content.Content;
import com.lemon.query.BaseQuery;
import com.lemon.service.ILemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
@Service
public class LemonServiceImpl extends BaseServiceImpl<Content,BaseQuery> implements ILemonService{
    @Resource
    private ILemonDao lemonDao;

    @Override
    protected IBaseDao<Content, BaseQuery> getBaseDao() {
        return lemonDao;
    }
}
