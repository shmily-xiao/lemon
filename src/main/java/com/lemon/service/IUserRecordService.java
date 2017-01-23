package com.lemon.service;

import com.lemon.domain.impl.user.UserRecord;
import com.lemon.query.BaseQuery;

import java.util.Optional;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public interface IUserRecordService extends IBaseService<UserRecord, BaseQuery>{
    /**
     * 根据用户id查询用户记录
     *
     * @param userId
     * @return
     */
    Optional<UserRecord> findUserRecordByUserId(Long userId);

    /**
     * 查询用户是否锁了自己的所有内容
     * @param userId
     * @return
     */
    Boolean isUserPrivateHisAllContents(Long userId);
}
