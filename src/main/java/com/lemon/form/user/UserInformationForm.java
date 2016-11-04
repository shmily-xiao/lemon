package com.lemon.form.user;

import com.lemon.domain.user.User;
import com.lemon.enums.GenderType;
import com.lemon.framework.mapping.annotation.MappingClass;
import com.lemon.framework.mapping.annotation.MappingRule;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by simpletour_Jenkin on 2016/8/9.
 */
@MappingClass(User.class)
public class UserInformationForm extends BaseForm{

    /**
     * 头像
     */
    @MappingRule
    private String avatar;

    /**
     * 性别
     */
    @NotNull
    @NotBlank
    @MappingRule
    private GenderType gender;

    /**
     * 名字
     */
    @NotNull
    @NotBlank
    @MappingRule
    private String name;

    /**
     * 昵称   用户的登录账户的名字
     */
    @NotNull
    @NotBlank
    @MappingRule
    private String nickName;

    /**
     * 生日
     */
    @NotNull
    @MappingRule
    private LocalDate birthday;

    /**
     * 邮箱
     */
    @MappingRule
    private String email;

    /**
     * 个人简介
     */
    @MappingRule
    private String profile;

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

}
