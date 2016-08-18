package com.lemon.domain.content;

import com.lemon.domain.BaseDomain;
import com.lemon.domain.interfaces.content.IContentPlan;

import java.time.LocalDateTime;

/**
 * Created by simpletour_Jenkin on 2016/8/18.
 *  内容的计划表（时间安排）
 */
public class ContentPlan extends BaseDomain implements IContentPlan {

    /**
     * 内容的id
     */
    private Long contentId;

    /**
     * 是否完成
     */
    private Boolean finished;

    /**
     * 希望完成的时间
     */
    private LocalDateTime expectTime;

    /**
     * 完成时间
     */
    private LocalDateTime finishedTime;

    /**
     * 是否需要发短信
     */
    private Boolean remind;

    /**
     * 用户的id
     */
    private Long userId;

    @Override
    public Long getContentId() {
        return contentId;
    }

    @Override
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public LocalDateTime getExpectTime() {
        return expectTime;
    }

    @Override
    public void setExpectTime(LocalDateTime expectTime) {
        this.expectTime = expectTime;
    }

    @Override
    public Boolean getFinished() {
        return finished;
    }

    @Override
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public LocalDateTime getFinishedTime() {
        return finishedTime;
    }

    @Override
    public void setFinishedTime(LocalDateTime finishedTime) {
        this.finishedTime = finishedTime;
    }

    @Override
    public Boolean getRemind() {
        return remind;
    }

    @Override
    public void setRemind(Boolean remind) {
        this.remind = remind;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
