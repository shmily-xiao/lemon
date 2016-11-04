package com.lemon.domain.impl.content;

import com.lemon.domain.impl.BaseDomain;
import com.lemon.domain.interfaces.content.IInteraction;
import com.lemon.enums.InteractionType;

/**
 * Created by simpletour_Jenkin on 2016/8/18.
 *  用户的交互表
 */
public class Interaction extends BaseDomain implements IInteraction{

    /**
     * 用户的id
     **/
    private Long userId;

    /**
     * 内容的id
     */
    private Long contentId;

    /**
     * 点赞 和 收藏 ，，行为的类型
     */
    private InteractionType action;


    @Override
    public InteractionType getAction() {
        return action;
    }

    @Override
    public void setAction(InteractionType action) {
        this.action = action;
    }

    @Override
    public Long getContentId() {
        return contentId;
    }

    @Override
    public void setContentId(Long contentId) {
        this.contentId = contentId;
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
