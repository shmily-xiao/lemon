package com.lemon.service;

import com.lemon.domain.User;
import com.lemon.enums.SignupType;
import com.lemon.query.BaseQuery;

import java.util.Optional;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
public interface IUserService extends IBaseService<User, BaseQuery>{

    /**
     * 创建一个新的用户
     *
     * 一个用户创建的时候会创建三条记录，一条在User 两条在 UserAccount
     *
     * @param user     要存入的用户的实体
     * @param account  用户的注册账号，手机号或者是QQ号
     * @return
     */
    Optional<User> createUser(User user, String account);
}
