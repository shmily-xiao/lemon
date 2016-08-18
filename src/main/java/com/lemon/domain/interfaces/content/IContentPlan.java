package com.lemon.domain.interfaces.content;

import com.lemon.domain.interfaces.ibase.ICreatedTime;

import java.time.LocalDateTime;

/**
 * Created by simpletour_Jenkin on 2016/8/18.
 * 事件的计划
 */
public interface IContentPlan extends ICreatedTime{
    // 是否已经完成
    Boolean getFinished();
    void setFinished(Boolean finished);

    // 期望完成的时间
    LocalDateTime getExpectTime();
    void setExpectTime(LocalDateTime expectTime);

    // 完成的时间
    LocalDateTime getFinishedTime();
    void setFinishedTime(LocalDateTime finishedTime);

    // 是否需要短信提醒
    Boolean getRemind();
    void setRemind(Boolean remind);

    // 这个事件是由谁创建的
    Long getUserId();
    void setUserId(Long userId);

    // 关联内容的id
    Long getContentId();
    void setContentId(Long contentId);

}
