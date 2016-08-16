package com.lemon.view.lemon;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2016/8/16.
 */
public class HomeLemonElementView {
    // 头像
    private String avatar;

    // 昵称
    private String nickName;

    // 创建时间
    private String createTime;

    // 完成时间
    private String finishedTime;

    // 剩余时间（天）
    private Integer leftTime;

    // 图片的链接
    private String imageUrl;

    // 描述
    private String description;

    // 点赞
    private Long likeCount;

    // 评论
    private List<String> comment;

    // 收藏
    private Long collectCount;

    // 用户的分组
    private List<String> userGroups;

    // 用户的id
    private Long userId;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(Integer leftTime) {
        this.leftTime = leftTime;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<String> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<String> userGroups) {
        this.userGroups = userGroups;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
