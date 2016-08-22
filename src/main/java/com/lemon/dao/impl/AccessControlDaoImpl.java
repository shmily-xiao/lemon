package com.lemon.dao.impl;

import com.lemon.dao.IAccessControlDao;
import com.lemon.domain.impl.access.AccessControl;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by simpletour_Jenkin on 2016/8/22.
 */
@Repository
public class AccessControlDaoImpl extends BaseDaoImpl<AccessControl, BaseQuery> implements IAccessControlDao{
}
