package com.lemon.dao.impl;

import com.lemon.dao.ILemonDao;
import com.lemon.domain.Lemon;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Component;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
@Component
public class LemonDaoImpl extends BaseDaoImpl<Lemon,BaseQuery> implements ILemonDao {
}
