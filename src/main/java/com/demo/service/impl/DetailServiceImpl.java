package com.demo.service.impl;

import com.demo.domain.Detail;
import com.demo.service.DetailService;
import com.demo.dao.DetailDao;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by simpletour_java on 2015/5/21.
 */
public class DetailServiceImpl implements DetailService {

    private DetailDao detailDao;


    public void setDetailDao(DetailDao detailDao) {
        this.detailDao = detailDao;
    }

    @Override
    public int insert(Detail detail) {
        return detailDao.insert(detail);
    }

    @Override
    public Detail findSendDetail(int id) {
        return detailDao.findSendDetail(id);
    }

    @Override
    public List<Detail> findAcceptDetail(String id) {
        return detailDao.findAcceptDetail(id);
    }

    @Override
    public int delete(int detail){
        return detailDao.delete(detail);
    }

    @Override
    public int find(int userId){
        return detailDao.find(userId);
    }

    @Override
    public List<Detail> allMessage(){
        return detailDao.allMessage();
    }

    @Override
    public List<Detail> findFollowDetail(int id) {
        return detailDao.findFollowDetail(id);
    }

    public Detail findDetailId(Timestamp sendTime){
        return detailDao.findDetailId(sendTime);
    }
    public int deleteFollow(int followId){
        return detailDao.deleteFollow(followId);
    }
}
