package com.lemon.query;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
public class BaseQuery {

    protected Long id;

    protected LocalDateTime createdTime;

    // 分页开始的地方
    protected Integer offset = 0;

    // 分页一页显示的条数
    protected Integer size = 10;

    // 总记录数
    protected Integer totalRecord;
    // 总页数
    protected Integer totalPage;

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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }
}
