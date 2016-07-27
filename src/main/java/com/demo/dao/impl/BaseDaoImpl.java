package com.demo.dao.impl;

import com.demo.dao.IDao;
import org.apache.poi.ss.formula.functions.T;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Created by simpletour_java on 2015/9/9.
 */
public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements IDao<T>{

    public String getNameSpace(){
        return this.getClass().getName();
    }
    @Override
    public void updateByConditions(T brandMobileInfoEntity) {
        this.getSqlSession().selectOne(getNameSpace() + "."+"updateByConditions",brandMobileInfoEntity);
    }
    public void insertByConditions(T brandMobileInfoEntity) {
        this.getSqlSession().selectOne(getNameSpace() + "."+"insertByConditions",brandMobileInfoEntity);
    }

}
