package com.demo.dao;


import com.demo.domain.BaseDomain;
import com.demo.query.BaseQuery;

import java.util.List;

/**
 * Created by simpletour_java on 2015/9/8.
 */
public interface IDao<T extends BaseDomain,M extends BaseQuery> {

    /**
     * 添加
     * @param object
     * @return
     */
    T insert(T object);

    /**
     * 根据条件删除
     * @param object
     */
    Integer delete(M object);

    /**
     * 根据id删除
     * @param id
     */
    Integer delete(Long id);

    /**
     * 更新
     * @param object
     * @return
     */
    T update(T object);

    /**
     * 查找
     * @param id
     * @return
     */
    T find(Long id);

    /**
     * 分页查询
     * @param object
     * @return
     */
    List<T> findByPage(M object);


    /**
     * 根据条件查询多条记录
     * @param object
     * @return
     */
    List<T> findEntities(M object);

}
