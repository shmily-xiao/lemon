package com.demo.domain.itfc;

import com.demo.domain.itfc.ibase.IName;

import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public interface ILemon extends IName{
    // 是否已经完成
    Boolean getFinished();
    void setFinished(Boolean finished);

    // 事情的描述
    String getDescription();
    void setDescription(String description);

    // 期望完成的时间
    Date getExpectTime();
    void setExpectTime(Date expectTime);

    // 完成的时间
    Date getFinishedTime();
    void setFinishedTime(Date finishedTime);

    // 事件的等级
    Integer getLevel();
    void setLevel(Integer level);

    // 这个事件是由谁创建的
    Long getUserId();
    void setUserId(Long userId);
}
