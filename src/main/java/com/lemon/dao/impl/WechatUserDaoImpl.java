package com.lemon.dao.impl;

import com.lemon.dao.IWechatUserDao;
import com.lemon.domain.WechatUser;
import com.lemon.query.BaseQuery;
import org.springframework.stereotype.Component;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
@Component
public class WechatUserDaoImpl extends BaseDaoImpl<WechatUser,BaseQuery> implements IWechatUserDao {
}
