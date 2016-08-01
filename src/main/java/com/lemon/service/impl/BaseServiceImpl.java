package com.lemon.service.impl;

import com.lemon.dao.IBaseDao;
import com.lemon.domain.BaseDomain;
import com.lemon.query.BaseQuery;
import com.lemon.service.IBaseService;

import java.util.List;
import java.util.Optional;

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
    public Optional<T> insert(T object) {
        return this.getBaseDao().insert(object) != 0 ? Optional.of(object) : Optional.empty();
    }

    @Override
    public Integer delete(M object) {
        return this.getBaseDao().delete(object);
    }

    @Override
    public Optional<T> update(T object) {
        return this.getBaseDao().update(object) != 0 ? Optional.of(object) : Optional.empty();
    }

    @Override
    public Optional<T> find(Long id) {
        T object = this.getBaseDao().find(id);
        return  object != null ? Optional.of(object):Optional.empty();
    }

    @Override
    public List<T> findByPage(M object) {
        return this.getBaseDao().findByPage(object);
    }

    @Override
    public List<T> findList(M object) {
        return this.getBaseDao().findEntities(object);
    }

    @Override
    public Optional<T> findOne(M object) {
        List<T> list = this.findList(object);
        return list != null && !list.isEmpty() ? Optional.of(list.get(0)) : Optional.empty();
    }
}
