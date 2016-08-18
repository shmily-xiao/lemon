package com.lemon.domain.content;

import com.lemon.domain.BaseDomain;
import com.lemon.domain.interfaces.content.IContent;
import com.lemon.enums.LemonType;

import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public class Content extends BaseDomain implements IContent {

    /**
     * 是否完成了
     */
    private Boolean finished;

    /**
     * 要完成的事情的标题
     */
    private String name;

    /**
     * 对这件事情的大概描述
     */
    private String description;

    /**
     * 图片，可能有多个,所以这里存取的时候都应该是一个json，方便以后扩展
     */
    private String images;

    /**
     * 自己定的大概的完成时间
     */
    private Date expectTime;

    /**
     * 完成的时间
     */
    private Date finishedTime;

    /**
     * 这件事情的等级,有 梦想 、 琐事 、 抒情 等
     */
    private LemonType type;

    /**
     * 是否用短信提醒用户
     */
    private Boolean remind;

    /**
     * 这件事情由谁来创建
     */
    private Long userId;

    /**
     * 此条记录是否公开
     * user有一个权限设置是对所有记录而言的，这个地方的权限只会对此条记录起作用
     */
    private Boolean isPublic;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Date getExpectTime() {
        return expectTime;
    }

    @Override
    public void setExpectTime(Date expectTime) {
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
    public Date getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Boolean getIsPublic() {
        return this.isPublic;
    }

    @Override
    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public String getImages() {
        return images;
    }

    @Override
    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public LemonType getType() {
        return type;
    }

    @Override
    public void setType(LemonType type) {
        this.type = type;
    }

    @Override
    public Boolean getRemind() {
        return remind;
    }

    @Override
    public void setRemind(Boolean remind) {
        this.remind = remind;
    }

}
