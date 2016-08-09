package com.lemon.form.user;

import com.lemon.enums.GenderType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
public class UserInformationForm extends BaseForm{

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    @NotNull
    @NotBlank
    private GenderType gender;

    /**
     * 名字
     */
    @NotNull
    @NotBlank
    private String name;

    /**
     * 昵称   用户的登录账户的名字
     */
    @NotNull
    @NotBlank
    private String nickName;

    /**
     * 生日
     */
    @NotNull
    private LocalDate birthday;

    /**
     * 电话号码
     */
    @NotBlank
    @NotNull
    private String mobile;

    /**
     * qq号码
     */
    @NotBlank
    @NotNull
    private String qqNo;

    /**
     * 邮箱
     */
    @NotBlank
    @NotNull
    private String email;

    /**
     * 个人简介
     */
    private String profile;

    /**
     * 用户是否公开自己的事件，或者是只能好友可见
     */
    private String zoneStatus;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    public String getZoneStatus() {
        return zoneStatus;
    }

    public void setZoneStatus(String zoneStatus) {
        this.zoneStatus = zoneStatus;
    }
}
