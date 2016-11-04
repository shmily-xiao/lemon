package com.lemon.dao.impl;

import com.lemon.dao.IInteractionDao;
import com.lemon.domain.impl.content.Interaction;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
@Repository
public class InteractionDaoImpl extends BaseDaoImpl<Interaction, BaseQuery> implements IInteractionDao {
}
