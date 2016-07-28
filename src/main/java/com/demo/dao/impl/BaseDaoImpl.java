package com.demo.dao.impl;

import com.demo.dao.IDao;
import com.demo.domain.BaseDomain;
import com.demo.query.BaseQuery;
import org.apache.poi.ss.formula.functions.T;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by simpletour_java on 2015/9/9.
 */
public abstract class BaseDaoImpl<T extends BaseDomain> extends SqlSessionDaoSupport implements IDao<T,BaseQuery>{

    public String getNameSpace(){
        return this.getClass().getName();
    }

    public final String opof(String namespace, String op){
        return String.format("%s,%s",namespace,op);
    }

    @Override
    public Integer delete(Long id) {
        return this.getSqlSession().delete(this.opof(this.getNameSpace(),"deleteById"),id);
    }

    @Override
    public T insert(T object) {
        return this.getSqlSession().insert(this.opof(this.getNameSpace(),"insert"),object) != 0 ? object : null;
    }

    @Override
    public Integer delete(BaseQuery object) {
        return this.getSqlSession().delete(this.opof(this.getNameSpace(),"delete"),object);
    }

    @Override
    public T update(T object) {
        return this.getSqlSession().update(this.opof(this.getNameSpace(),"update"),object) != 0 ? object : null;
    }

    @Override
    public T find(Long id) {
        return this.getSqlSession().selectOne(this.opof(this.getNameSpace(),"findById"),id);
    }

    @Override
    public List<T> findByPage(BaseQuery object) {
        return this.getSqlSession().selectList(this.opof(this.getNameSpace(),"findByPage"),object);
    }

    @Override
    public List<T> findEntities(BaseQuery object) {
        return this.getSqlSession().selectList(this.opof(this.getNameSpace(),"findEntities"),object);
    }
}
