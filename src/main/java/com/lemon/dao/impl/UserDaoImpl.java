package com.lemon.dao.impl;

import com.lemon.dao.IUserDao;
import com.lemon.domain.User;
import com.lemon.query.BaseQuery;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User,BaseQuery> implements IUserDao {


    @Resource(name = "sqlSession")
    protected SqlSessionTemplate sqlSessionTemplate;

    {
        this.setSqlSessionTemplate(sqlSessionTemplate);
    }
}
