package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.dao.IInteractionDao;
import com.lemon.domain.impl.content.Interaction;
import com.lemon.query.BaseQuery;
import com.lemon.query.content.InteractionQuery;
import com.lemon.service.IInteractionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
@Service
public class InteractionServiceImpl extends BaseServiceImpl<Interaction, BaseQuery> implements IInteractionService {

    @Resource
    private IInteractionDao interactionDao;

    @Override
    protected IBaseDao<Interaction, BaseQuery> getBaseDao() {
        return this.interactionDao;
    }

    @Override
    public List<Interaction> findByContentId(Long contentId) {

        InteractionQuery interactionQuery = new InteractionQuery();
        interactionQuery.setContentId(contentId);
        return interactionDao.findEntities(interactionQuery);
    }
}
