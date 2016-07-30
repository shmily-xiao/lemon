package com.lemon.query;

import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/28.
 */
public class BaseQuery {

    private Long id;

    private Date createdTime;

    // 分页开始的地方
    private Integer offset = 0;

    // 分页一页显示的条数
    private Integer size = 10;

    // 总记录数
    private Integer totalRecord;
    // 总页数
    private Integer totalPage;

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
