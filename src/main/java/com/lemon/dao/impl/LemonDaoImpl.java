package com.lemon.dao.impl;

import com.lemon.dao.ILemonDao;
import com.lemon.domain.content.Content;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
@Repository
public class LemonDaoImpl extends BaseDaoImpl<Content,BaseQuery> implements ILemonDao {
}
