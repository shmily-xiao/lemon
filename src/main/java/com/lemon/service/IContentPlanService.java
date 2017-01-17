package com.lemon.service;

import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.query.BaseQuery;

import java.util.Optional;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public interface IContentPlanService extends IBaseService<ContentPlan, BaseQuery> {
    public Optional<ContentPlan> findByContentId(Long contentId);
}
