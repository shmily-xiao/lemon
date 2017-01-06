package com.lemon.service.bo.lemonContent;

import com.lemon.enums.ContentType;
import com.lemon.enums.StrategyType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/6.
 */
public class LemonContentAddBo {

    // 标题
    private String title;
    // 内容 或者是描述 description
    private String content;
    // 完成的时间
    private LocalDateTime finishedTime;
    // 内容的类型
    private ContentType contentsType;
    // 是否需要短信提醒
    private Boolean needMessage;
    //访问控制
    private StrategyType strategyType;
    // 照片
    private List<String> images;
    // 用户id
    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(LocalDateTime finishedTime) {
        this.finishedTime = finishedTime;
    }

    public ContentType getContentsType() {
        return contentsType;
    }

    public void setContentsType(ContentType contentsType) {
        this.contentsType = contentsType;
    }

    public Boolean getNeedMessage() {
        return needMessage;
    }

    public void setNeedMessage(Boolean needMessage) {
        this.needMessage = needMessage;
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(StrategyType strategyType) {
        this.strategyType = strategyType;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
