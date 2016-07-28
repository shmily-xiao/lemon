package com.demo.domain.interfaces;

import com.demo.domain.interfaces.ibase.ICreatedTime;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
public interface IFriendship extends ICreatedTime{

    // 朋友的id
    Long getFriendId();
    void setFriendId(Long friendId);


    // 朋友雷系，特别关注，还是普通
    String getType() ;
    void setType(String type);

    // 用户的id
    Long getUserId();
    void setUserId(Long userId) ;

    // 用户对朋友的分类
    String getGroup();
    void setGroup(String group);
}
