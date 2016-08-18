package com.lemon.domain.interfaces.friend;

import com.lemon.domain.interfaces.ibase.ICreatedTime;
import com.lemon.enums.FriendType;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 * 用户的好友关系
 */
public interface IFriendship extends ICreatedTime{

    // 朋友的id
    Long getFriendId();
    void setFriendId(Long friendId);


    // 朋友类型，特别关注，还是普通
    FriendType getType() ;
    void setType(FriendType type);

    // 用户的id
    Long getUserId();
    void setUserId(Long userId) ;

    // 用户对朋友的分组
    Long getFriendGroupId();
    void setFriendGroupId(Long friendGroupId);

    // 权限控制
    Long getAccessControlId();
    void setAccessControlId(Long accessControlId);

}
