package com.lemon.dao.impl;

import com.lemon.dao.CookiesDao;
import com.lemon.domain.Cookies;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by simpletour_java on 2015/6/4.
 */
//@Repository
public class CookiesDaoImpl extends SqlSessionDaoSupport implements CookiesDao {

//    @Resource(name = "sqlSession")
//    private SqlSessionTemplate sqlSessionTemplate;
//
//    {
//        this.setSqlSessionTemplate(sqlSessionTemplate);
//    }

    public String getNameSpace(){
        return this.getClass().getName();
    }

    @Override
    public Cookies findCookies(Long userId) {
        return this.getSqlSession().selectOne(getNameSpace() + "." + "findCookies",userId);
    }

    @Override
    public int insertCookies(Cookies cookies) {
        return this.getSqlSession().insert(getNameSpace() + "." + "insertCookies",cookies);
    }

    @Override
    public int updateCookies(Cookies cookies) {
        return this.getSqlSession().update(getNameSpace() + "." + "updateCookies",cookies);
    }
}
