package com.lemon.view.lemon.contents;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/18.
 */
public class PersonalCenterContentsView {
    // id
    private Long id;

    // 创建时间
    private String createTime;

    // 完成时间
    private String finishedTime;

    // 剩余时间（天）
    private Integer leftTime;

    // 图片的链接
    private List<String> imageUrl;

    // 标题
    private String title;

    // 描述
    private String description;

    // 内容的类型
    private String type;

    // ContentThingsStatusEnum 对时间的状态
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Integer getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(Integer leftTime) {
        this.leftTime = leftTime;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
