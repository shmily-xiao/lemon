package com.lemon.domain.interfaces.content;

import com.lemon.domain.interfaces.ibase.ICreatedTime;
import com.lemon.enums.InteractionType;

/**
 * Created by simpletour_Jenkin on 2016/8/18.
 * 关系表， 用户的交互表
 */
public interface IInteraction extends ICreatedTime{
    // 用户的id
    Long getUserId();
    void setUserId(Long userId);

    // 用户的内容的id
    Long getContentId();
    void setContentId(Long contentId);

    // 交互的类型
    InteractionType getAction();
    void setAction(InteractionType type);

}
