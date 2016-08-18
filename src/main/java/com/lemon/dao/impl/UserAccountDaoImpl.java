package com.lemon.dao.impl;

import com.lemon.dao.IUserAccountDao;
import com.lemon.domain.user.UserAccount;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by simpletour_Jenkin on 2016/8/12.
 */
@Repository
public class UserAccountDaoImpl extends BaseDaoImpl<UserAccount, BaseQuery> implements IUserAccountDao{
}
