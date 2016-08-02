package com.lemon.dao.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.domain.BaseDomain;
import com.lemon.query.BaseQuery;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by simpletour_java on 2015/9/9.
 */
@Component
public abstract class BaseDaoImpl<T extends BaseDomain, M extends BaseQuery> extends SqlSessionDaoSupport implements IBaseDao<T,M> {

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

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
    public Integer insert(T object) {
        return this.getSqlSession().insert(this.opof(this.getNameSpace(),"insert"),object);
    }

    @Override
    public Integer delete(M object) {
        return this.getSqlSession().delete(this.opof(this.getNameSpace(),"delete"),object);
    }

    @Override
    public Integer update(T object) {
        return this.getSqlSession().update(this.opof(this.getNameSpace(),"update"),object);
    }

    @Override
    public T find(Long id) {
        return this.getSqlSession().selectOne(this.opof(this.getNameSpace(),"findById"),id);
    }

    @Override
    public List<T> findByPage(M object) {
        return this.getSqlSession().selectList(this.opof(this.getNameSpace(),"findByPage"),object);
    }

    @Override
    public List<T> findEntities(M object) {
        return this.getSqlSession().selectList(this.opof(this.getNameSpace(),"findEntities"),object);
    }

    @Override
    public Integer count(M object) {
        return this.getSqlSession().selectOne(this.opof(this.getNameSpace(),"count"),object);
    }
}
