package com.lemon.domain.impl.user;

import com.lemon.domain.impl.BaseDomain;
import com.lemon.domain.interfaces.user.IUser;
import com.lemon.enums.GenderType;
import com.lemon.enums.SignupType;

import java.time.LocalDate;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 *
 * 用户的基本信息表
 */
public class User extends BaseDomain implements IUser {

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private GenderType gender;

    /**
     * 名字
     */
    private String name;

    /**
     * 用户在我们系统的唯一账号，和手机号，QQ都决定了同一个账号
     */
    private String account="";

    /**
     * 昵称   用户的登录账户的名字
     */
    private String nickName;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 电话号码,不是数据库的字段，但是应该属于用户的一部分
     */
    private String mobile;

    /**
     * qq号码,不是数据库的字段，但是应该属于用户的一部分
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

//    /**
//     * 用户的类型，或者是等级,默认为 新手
//     */
//    private UserType type = UserType.NEWBIE;

//    /**
//     * 成就值，得分
//     */
//    private Long score = 0L;
//
//    /**
//     * 用户是否公开自己的事件，或者是只能好友可见
//     */
//    private ZoneStatus zoneStatus = ZoneStatus.PUBLIC;

    /**
     * 注册类型，最初是手机号、和qq号
     */
    private SignupType signupType;

//    /**
//     * 短信的条数
//     */
//    private Long smsCount;

    /**
     * 是否可以更改账号
     */
    private Boolean modifyAvailable = Boolean.TRUE;

    public User(){}

    public User(String account, String nickName, String password, String salt, SignupType signupType) {
        this.account = account;
        this.nickName = nickName;
        this.password = password;
        this.salt = salt;
        this.signupType = signupType;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(LocalDate birthday) {
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
    public GenderType getGender() {
        return gender;
    }

    @Override
    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
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
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getProfile() {
        return profile;
    }

    @Override
    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public SignupType getSignupType() {
        return signupType;
    }

    @Override
    public void setSignupType(SignupType signupType) {
        this.signupType = signupType;
    }

    @Override
    public Boolean getModifyAvailable() {
        return modifyAvailable;
    }

    @Override
    public void setModifyAvailable(Boolean modifyAvailable) {
        this.modifyAvailable = modifyAvailable;
    }
}
