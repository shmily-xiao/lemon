package com.lemon.service;

import com.lemon.domain.impl.content.Interaction;
import com.lemon.query.BaseQuery;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public interface IInteractionService extends IBaseService<Interaction, BaseQuery> {
    public List<Interaction> findByContentId(Long contentId);
}
