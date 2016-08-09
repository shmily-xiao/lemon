package com.lemon.domain;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public class BaseDomain{
    protected Long id;

    protected LocalDateTime createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
