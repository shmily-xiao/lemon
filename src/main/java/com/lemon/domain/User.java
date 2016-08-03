package com.lemon.domain;

import com.lemon.domain.interfaces.IUser;
import com.lemon.enums.UserType;
import com.lemon.enums.ZoneStatus;

import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public class User extends BaseDomain implements IUser {

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String gender;

    /**
     * 名字
     */
    private String name;

    /**
     * 昵称   用户的登录账户的名字
     */
    private String nickName;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * qq号码
     */
    private String qqNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码（MD5)
     */
    private String password;

    /**
     * 随机数
     */
    private String salt;

    /**
     * 状态，表示该用户是否是可用的
     */
    private String status = "true";

    /**
     * 用户的类型，或者是等级,默认为 新手
     */
    private UserType type = UserType.NEWBIE;

    /**
     * 成就值，得分
     */
    private Long score = 0L;

    /**
     * 用户是否公开自己的事件，或者是只能好友可见
     */
    private ZoneStatus zoneStatus = ZoneStatus.PUBLIC;

    public User(){}

    public User(String mobile, String nickName, String password, String salt) {
        this.mobile = mobile;
        this.nickName = nickName;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    @Override
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    @Override
    public String getSalt() {
        return salt;
    }

    @Override
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public Long getScore() {
        return score;
    }

    @Override
    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public ZoneStatus getZoneStatus() {
        return zoneStatus;
    }
    @Override
    public void setZoneStatus(ZoneStatus zoneStatus) {
        this.zoneStatus = zoneStatus;
    }
}
