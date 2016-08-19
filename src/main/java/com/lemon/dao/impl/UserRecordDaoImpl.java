package com.lemon.dao.impl;

import com.lemon.dao.IUserRecordDao;
import com.lemon.domain.user.UserRecord;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
@Repository
public class UserRecordDaoImpl extends BaseDaoImpl<UserRecord,BaseQuery> implements IUserRecordDao {
}
