package com.demo.service;

import com.demo.domain.Detail;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by simpletour_java on 2015/5/21.
 */
public interface DetailService {
    int insert(Detail detail);
    List<Detail> findAcceptDetail(String id);
    Detail findSendDetail(int id);
    List<Detail> findFollowDetail(int id);
    int delete(int detail);
    int find(int userId);
    List<Detail> allMessage();
    Detail findDetailId(Timestamp sendTime);
    int deleteFollow(int followId);
}
