package com.demo.dao;

import com.demo.domain.BrandMobileInfoEntity;

/**
 * Created by simpletour_java on 2015/9/8.
 */
public interface IDao<T> {
    void updateByConditions(T brandMobileInfoEntity);
    void insertByConditions(T brandMobileInfoEntity);
}
