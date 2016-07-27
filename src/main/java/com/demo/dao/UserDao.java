package com.demo.dao;

import com.demo.domain.BrandMobileInfoEntity;
import com.demo.domain.User;

import java.util.List;

/**
 * Created by YB on 2015/5/21.
 */
public interface UserDao{
    int insert(User user);
    int delete(String id);
    int findName(String id);
    List<User> list();
    int findUser(User user);
    int addRoot(String id);
    int deleteRoot(String id);
    int findRoot(String id);
    User findUserName(String id);
    int findSalt(String id);
//    void updateByConditions(BrandMobileInfoEntity brandMobileInfoEntity);
//    void insertByConditions(BrandMobileInfoEntity brandMobileInfoEntity);

}
