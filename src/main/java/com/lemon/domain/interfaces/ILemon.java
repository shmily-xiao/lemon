package com.lemon.domain.interfaces;

import com.lemon.domain.interfaces.ibase.IName;
import com.lemon.enums.LemonType;

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

    // 这个事件是由谁创建的
    Long getUserId();
    void setUserId(Long userId);

    // 此条记录是否公开
    Boolean getIsPublic();
    void setIsPublic(Boolean isPublic);

    // 事件的类型
    LemonType getType();
    void setType(LemonType type);

    // 是否需要短信提醒
    Boolean getRemind();
    void setRemind(Boolean remind);

    // 照片
    String getImages();
    void setImages(String images);
}
