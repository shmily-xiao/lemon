package com.demo.dao.impl;

import com.demo.dao.DetailDao;
import com.demo.domain.Detail;
import com.demo.service.DetailService;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by simpletour_java on 2015/5/21.
 */
public class DetailDaoImpl extends SqlSessionDaoSupport implements DetailDao {

    public String getNameSpace(){
        return this.getClass().getName();
    }

    @Override
    public int insert(Detail detail) {
        return this.getSqlSession().insert(getNameSpace() + "." + "insert", detail);
    }

    @Override
    public List<Detail> findAcceptDetail(String id) {
        return this.getSqlSession().selectList(getNameSpace() + "." + "findAcceptDetail", id);
    }

    @Override
    public Detail findSendDetail(int id) {
        return this.getSqlSession().selectOne(getNameSpace() + "." + "findSendDetail", id);
    }

    @Override
    public List<Detail> findFollowDetail(int id) {
        return this.getSqlSession().selectList(getNameSpace() + "." + "findFollowDetail", id);
    }

    @Override
    public int delete(int detailId){
        return this.getSqlSession().delete(getNameSpace() + "." + "delete", detailId);
    }
    @Override
    public int deleteFollow(int followId){
        return this.getSqlSession().delete(getNameSpace() + "." + "deleteFollow", followId);
    }

    @Override
    public int find(int userId){
        return this.getSqlSession().selectOne(getNameSpace() + "." + "find", userId);
    }

    @Override
    public List<Detail> allMessage(){
        return this.getSqlSession().selectList(getNameSpace() + "." + "allMessage");
    }

    public Detail findDetailId(Timestamp sendTime){
        return this.getSqlSession().selectOne(getNameSpace() + "."+"findDetailId",sendTime);
    }
}
