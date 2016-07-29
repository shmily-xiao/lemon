package com.lemon.domain;

import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public class BaseDomain{
    protected Long id;

    protected Date createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
