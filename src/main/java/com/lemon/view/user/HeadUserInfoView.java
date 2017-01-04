package com.lemon.view.user;

import com.lemon.enums.UserType;
import com.lemon.view.BaseView;

/**
 * Created by simpletour_Jenkin on 2017/1/4.
 */
public class HeadUserInfoView extends BaseView{
    /**
     * 用户的类型，或者是等级,默认为 新手
     */
    private String type = UserType.DEFAULT.getValue();

    /**
     * 成就值，得分
     */
    private Long score = 0L;
    /**
     * 昵称   用户的登录账户的名字
     */
    private String nickName = "小懒萌";

    /**
     * 头像
     */
    private String avatar = "/img/user48.ico";

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
