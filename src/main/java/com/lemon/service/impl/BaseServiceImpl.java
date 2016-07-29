package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.domain.BaseDomain;
import com.lemon.query.BaseQuery;
import com.lemon.service.IBaseService;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
public abstract class BaseServiceImpl<T extends BaseDomain, M extends BaseQuery> implements IBaseService<T, M> {

    // // TODO: 2016/7/29 启动之后试一试注入baseDao
//    private IBaseDao<T,M> baseDao;

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
        return this.getBaseDao().findByPage(object);
    }

    @Override
    public List<T> findEntities(M object) {
        return this.getBaseDao().findEntities(object);
    }
}
