package com.lemon.domain.interfaces;

import com.lemon.domain.interfaces.ibase.IName;
import com.lemon.enums.UserType;
import com.lemon.enums.ZoneStatus;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public interface IUser extends IName{
    // 头像
    String getAvatar();
    void setAvatar(String avatar);

    // 性别
    String getGender();
    void setGender(String gender);

    // 昵称
    String getNickName();
    void setNickName(String nickName);

    // 生日
    LocalDate getBirthday();
    void setBirthday(LocalDate birthday);

    // 手机号
    String getMobile();
    void setMobile(String mobile);

    // qq号码
    String getQqNo();
    void setQqNo(String qqNo);

    // 邮箱
    String getEmail();
    void setEmail(String email);

    // 密码
    String getPassword();
    void setPassword(String password);

    // 盐，随机数
    String  getSalt();
    void setSalt(String salt);

    // 用户的状态，可能是被冻结了，又或者是其他的状态
    String getStatus();
    void setStatus(String status);

    //用户的类型, 用于表示用户的等级
    UserType getType();
    void setType(UserType type);

    // 用户的得分，或者是叫做 成就，每完成一件自己想做的事情就加分，理论上没有上线
    // 但是以后可以通过成就值给用户划分等级
    Long getScore();
    void setScore(Long score);

    // 用户是否公开自己的事件，或者是只能好友可见
    ZoneStatus getZoneStatus();
    void setZoneStatus(ZoneStatus zoneStatus);

    // 个人简介
    String getProfile();
    void setProfile(String profile);

}
