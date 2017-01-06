package com.lemon.service;

import com.lemon.domain.impl.content.Content;
import com.lemon.query.BaseQuery;
import com.lemon.service.bo.lemonContent.LemonContentAddBo;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
public interface IContentService extends IBaseService<Content,BaseQuery> {
    /**
     * 添加用户的记录
     * 1.添加content表
     * 2.添加contentPlan表
     * 3.添加accessController表
     * 4.修改用户UserRecord表记录（更新成就值和达到范围之后更新用户等级）
     * @param lemonContentAddBo
     * @return
     */
    public Boolean addLemonContent(LemonContentAddBo lemonContentAddBo);
}
