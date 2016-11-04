package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IContentPlanDao;
import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.query.BaseQuery;
import com.lemon.service.IContentPlanService;
import org.jvnet.hk2.annotations.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
@Service
public class ContentPlanServiceImpl extends BaseServiceImpl<ContentPlan, BaseQuery> implements IContentPlanService {

    @Resource
    private IContentPlanDao contentPlanDao;

    @Override
    protected IBaseDao<ContentPlan, BaseQuery> getBaseDao() {
        return this.contentPlanDao;
    }
}
