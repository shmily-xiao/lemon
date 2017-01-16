package com.lemon.query.lemon;

import com.lemon.enums.InteractionType;
import com.lemon.query.BaseQuery;

/**
 * Created by simpletour_Jenkin on 2017/1/16.
 */
public class InteractionQuery extends BaseQuery{

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public InteractionType getAction() {
        return action;
    }

    public void setAction(InteractionType action) {
        this.action = action;
    }
}
