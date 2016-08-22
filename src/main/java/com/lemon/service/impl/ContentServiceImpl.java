package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IContentDao;
import com.lemon.domain.impl.content.Content;
import com.lemon.query.BaseQuery;
import com.lemon.service.IContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content,BaseQuery> implements IContentService {
    @Resource
    private IContentDao lemonDao;

    @Override
    protected IBaseDao<Content, BaseQuery> getBaseDao() {
        return lemonDao;
    }
}
