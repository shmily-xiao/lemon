package com.lemon.dao.impl;

import com.lemon.dao.IContentDao;
import com.lemon.domain.impl.content.Content;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
@Repository
public class ContentDaoImpl extends BaseDaoImpl<Content,BaseQuery> implements IContentDao {
}
