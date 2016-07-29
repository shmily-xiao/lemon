package com.lemon.domain;

import com.lemon.domain.interfaces.ILemon;

import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public class Lemon extends BaseDomain implements ILemon{

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
     * 这件事情的等级。这个等级由用户自己定义
     */
    private Integer level;

    /**
     * 这件事情由谁来创建
     */
    private Long userId;

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
    public Integer getLevel() {
        return level;
    }

    @Override
    public void setLevel(Integer level) {
        this.level = level;
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


}
