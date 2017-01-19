package com.lemon.view.friends;

/**
 * Created by simpletour_Jenkin on 2017/1/19.
 */
public class FriendElementView {

    private Long id;

    private String nickName="";

    private String sex="未知";

    private String birthday="未填写";

    private String profile="ta很懒什么也没有留下。";

    private String avatar="/img/ship.jpg";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
