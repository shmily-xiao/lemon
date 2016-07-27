package com.demo.service.impl;

import com.demo.dao.DetailDao;
import com.demo.dao.UserDao;
import com.demo.domain.BrandMobileInfoEntity;
import com.demo.domain.User;
import com.demo.service.UserService;

import java.util.List;

/**
 * Created by YB on 2015/5/21.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private DetailDao detailDao; //注入 在application-service.xml 中 name属性所指向的set放法

    public DetailDao getDetailDao() {
        return detailDao;
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int delete(String id) {
        return userDao.delete(id);
    }

    @Override
    public int findName(String id) {
        return userDao.findName(id);
    }

    @Override
    public List<User> list() {
        List<User> list = userDao.list();
        return list;
    }

    public int findUser(User user){
        return userDao.findUser(user);
    }

    //注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDetailDao(DetailDao detailDao) {
        this.detailDao = detailDao;
    }

    public int addRoot(String id){
        return userDao.addRoot(id);
    }

    public int deleteRoot(String id){
        return userDao.deleteRoot(id);
    }

    public int findRoot(String id){
        return userDao.findRoot(id);
    }

    public User findUserName(String id){
        return userDao.findUserName(id);
    }

    public int findSalt(String id){
        return userDao.findSalt(id);
    }

//    @Override
//    public void updateByConditions(BrandMobileInfoEntity brandMobileInfoEntity) {
//         userDao.updateByConditions(brandMobileInfoEntity);
//    }
//
//    @Override
//    public void insertByConditions(BrandMobileInfoEntity brandMobileInfoEntity) {
//         userDao.insertByConditions(brandMobileInfoEntity);
//    }
}
