package com.lemon.query.lemon;

import com.lemon.query.BaseQuery;

import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
public class LemonQuery extends BaseQuery{
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
     * 自己定的大概的完成时间
     */
    private Date expectTime;

    /**
     * 完成的时间
     */
    private Date finishedTime;

    /**
     * 用户自己定义的等级
     */
    private Integer level;

    /**
     * 谁来创建的
     */
    private Long userId;

    /**
     * 此条记录是否公开
     */
    private Boolean isPublic;


    public LemonQuery() {
    }

    public LemonQuery(String description, Date expectTime, Boolean finished, Date finishedTime, Integer level, String name, Long userId, Boolean isPublic) {
        this.description = description;
        this.expectTime = expectTime;
        this.finished = finished;
        this.finishedTime = finishedTime;
        this.level = level;
        this.name = name;
        this.userId = userId;
        this.isPublic = isPublic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}
