package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IContentPlanDao;
import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.query.BaseQuery;
import com.lemon.query.content.ContentPlanQuery;
import com.lemon.service.IContentPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<ContentPlan> findByContentId(Long contentId) {
        ContentPlanQuery query = new ContentPlanQuery();
        query.setContentId(contentId);
        List<ContentPlan> contentPlans = contentPlanDao.findEntities(query);
        return contentPlans != null && !contentPlans.isEmpty() ? Optional.of(contentPlans.get(0)) : Optional.empty();
    }
}
