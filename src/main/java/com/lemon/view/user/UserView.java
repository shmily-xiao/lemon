package com.lemon.view.user;

import com.lemon.domain.User;

import java.util.Date;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class UserView {
    private Long id;
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
    private Integer year;
    private Integer month;
    private Integer day;

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
     * 个人简介
     */
    private String profile;

    /**
     * 用户的类型，或者是等级,默认为 新手
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

    public UserView() {}

    public UserView(User user){
        this.id = user.getId();
        this.avatar = user.getAvatar();
        this.gender = user.getGender();
        this.name = user.getName();
        this.nickName = user.getNickName();
        if (user.getBirthday()!=null) {
            this.year = user.getBirthday().getYear();
            this.month = user.getBirthday().getMonthValue();
            this.day = user.getBirthday().getDayOfMonth();
        }
        this.mobile = user.getMobile();
        this.qqNo = user.getQqNo();
        this.email = user.getEmail();
        this.profile = user.getProfile();
        this.type = user.getType().getName();
        this.score = user.getScore();
        this.zoneStatus = user.getZoneStatus().getName();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getZoneStatus() {
        return zoneStatus;
    }

    public void setZoneStatus(String zoneStatus) {
        this.zoneStatus = zoneStatus;
    }
}
