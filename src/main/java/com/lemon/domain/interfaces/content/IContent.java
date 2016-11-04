package com.lemon.domain.interfaces.content;

import com.lemon.domain.interfaces.ibase.ICreatedTime;
import com.lemon.enums.ContentType;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 * 内容表，评论的内容表
 */
public interface IContent extends ICreatedTime{
//    // 是否已经完成
//    Boolean getFinished();
//    void setFinished(Boolean finished);

    // 事情的描述
    String getDescription();
    void setDescription(String description);

//    // 期望完成的时间
//    Date getExpectTime();
//    void setExpectTime(Date expectTime);
//
//    // 完成的时间
//    Date getFinishedTime();
//    void setFinishedTime(Date finishedTime);

    // 这个事件是由谁创建的
    Long getUserId();
    void setUserId(Long userId);

//    // 此条记录是否公开
//    Boolean getIsPublic();
//    void setIsPublic(Boolean isPublic);

    // 事件的类型
    ContentType getType();
    void setType(ContentType type);

//    // 是否需要短信提醒
//    Boolean getRemind();
//    void setRemind(Boolean remind);

    // 照片
    String getImages();
    void setImages(String images);

    // 是否删除
    Boolean getDel();
    void setDel(Boolean del);

    // 标题
    String getTitle();
    void setTitle(String title);
}
