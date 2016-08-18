package com.lemon.dao.impl;

import com.lemon.dao.IWechatUserDao;
import com.lemon.domain.wechatUser.WechatUser;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
@Repository
public class WechatUserDaoImpl extends BaseDaoImpl<WechatUser,BaseQuery> implements IWechatUserDao {
}
