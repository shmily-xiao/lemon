package com.demo.dao.impl;

import com.demo.dao.UserDao;
import com.demo.domain.BrandMobileInfoEntity;
import com.demo.domain.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by YB on 2015/5/21.
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    public String getNameSpace(){
        return this.getClass().getName();
    }

    @Override
    public int insert(User user) {
        return this.getSqlSession().insert(getNameSpace() + "." + "insert", user);
    }

    @Override
    public int delete(String id) {
        return this.getSqlSession().delete(getNameSpace() + "." + "deleteUser", id);
    }

    @Override
    public int findName(String id) {
        return this.getSqlSession().selectOne(getNameSpace() + "." + "find", id);
    }

    @Override
    public List<User> list() {
        return this.getSqlSession().selectList(getNameSpace() + "." + "list");
    }

    @Override
    public int findUser(User user){
        return this.getSqlSession().selectOne(getNameSpace() + "." + "findUser", user);
    }

    public int addRoot(String id){
        return this.getSqlSession().update(getNameSpace() + "." + "addRoot", id);
    }

    public int deleteRoot(String id){
        return this.getSqlSession().update(getNameSpace() + "." +"deleteRoot" , id);
    }

    public int findRoot(String id){
        return this.getSqlSession().selectOne(getNameSpace() + "." + "findRoot", id);
    }

    public User findUserName(String id){
        return this.getSqlSession().selectOne(getNameSpace() + "."+"findUserName",id);
    }

    public int findSalt(String id){
        return this.getSqlSession().selectOne(getNameSpace() + "." + "findSalt",id);
    }

//    @Override
//    public void updateByConditions(BrandMobileInfoEntity brandMobileInfoEntity) {
//        this.getSqlSession().selectOne(getNameSpace() + "."+"updateByConditions",brandMobileInfoEntity);
//    }
//    public void insertByConditions(BrandMobileInfoEntity brandMobileInfoEntity) {
//         this.getSqlSession().selectOne(getNameSpace() + "."+"insertByConditions",brandMobileInfoEntity);
//    }
}
