package com.demo.dao.impl;

import com.demo.dao.CookiesDao;
import com.demo.domain.Cookies;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Created by simpletour_java on 2015/6/4.
 */
public class CookiesDaoImpl extends SqlSessionDaoSupport implements CookiesDao {

    public String getNameSpace(){
        return this.getClass().getName();
    }

    @Override
    public Cookies findCookies(String id) {
        return this.getSqlSession().selectOne(getNameSpace() + "." + "findCookies",id);
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
