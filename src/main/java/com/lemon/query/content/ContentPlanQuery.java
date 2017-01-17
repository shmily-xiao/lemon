package com.lemon.query.content;

import com.lemon.query.BaseQuery;

import java.time.LocalDateTime;

/**
 * Created by simpletour_Jenkin on 2017/1/17.
 */
public class ContentPlanQuery extends BaseQuery{
    /**
     * 内容的id
     */
    private Long contentId;

    /**
     * 是否完成
     */
    private Boolean finished;

    /**
     * 是否需要发短信
     */
    private Boolean remind;

    /**
     * 用户的id
     */
    private Long userId;

    /**
     * 希望完成的时间
     */
    private LocalDateTime expectTime;

    /**
     * 完成时间
     */
    private LocalDateTime finishedTime;



    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean getRemind() {
        return remind;
    }

    public void setRemind(Boolean remind) {
        this.remind = remind;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(LocalDateTime expectTime) {
        this.expectTime = expectTime;
    }

    public LocalDateTime getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(LocalDateTime finishedTime) {
        this.finishedTime = finishedTime;
    }
}
