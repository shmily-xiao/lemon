package com.demo.domain;

import com.demo.domain.interfaces.IUser;

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
     * 昵称
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
     * 状态
     */
    private String status;

    /**
     * 用户的类型，或者是等级
     */
    private String type;

    /**
     * 成就值，得分
     */
    private Long score;

    /**
     * 用户是否公开自己的事件，或者是只能好友可见
     */
    private String zoneStatus;

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
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getZoneStatus() {
        return zoneStatus;
    }
    @Override
    public void setZoneStatus(String zoneStatus) {
        this.zoneStatus = zoneStatus;
    }
}
