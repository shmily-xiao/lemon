package com.lemon.query.content;

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
    // 2017-1-18 原来数是InteractionType
    // 报错：com.lemon.enums.InteractionType and java.lang.String
    private String action;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
