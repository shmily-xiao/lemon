package com.demo.service.impl;

import com.demo.dao.IBaseDao;
import com.demo.domain.BaseDomain;
import com.demo.query.BaseQuery;
import com.demo.service.IBaseService;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
public abstract class BaseServiceImpl<T extends BaseDomain, M extends BaseQuery> implements IBaseService<T, M> {

    private IBaseDao<T,M> baseDao;

    protected abstract IBaseDao<T, M> getBaseDao();

    @Override
    public Integer delete(Long id) {
        return this.getBaseDao().delete(id);
    }

    @Override
    public T insert(T object) {
        return this.getBaseDao().insert(object) != 0 ? object : null;
    }

    @Override
    public Integer delete(M object) {
        return this.getBaseDao().delete(object);
    }

    @Override
    public T update(T object) {
        return this.getBaseDao().update(object) != 0 ? object : null;
    }

    @Override
    public T find(Long id) {
        return this.getBaseDao().find(id);
    }

    @Override
    public List<T> findByPage(M object) {
        return null;
    }

    @Override
    public List<T> findEntities(M object) {
        return null;
    }
}
