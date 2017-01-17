package com.lemon.query.content;

import com.lemon.enums.ContentType;
import com.lemon.query.BaseQuery;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/29.
 */
public class LemonContentQuery extends BaseQuery{
    /**
     * 要完成的事情的标题
     */
    private String title;

    /**
     * 对这件事情的大概描述
     */
    private String description;

    /**
     * 图片，可能有多个,所以这里存取的时候都应该是一个json，方便以后扩展
     */
    private String images;

    /**
     * 这件事情的等级,有 梦想 、 琐事 、 抒情 等
     */
    private ContentType type;

    /**
     * 这件事情由谁来创建
     */
    private Long userId;

    /**
     * 是否删除了
     */
    private Boolean del;


    public LemonContentQuery() {
    }

    // 第一个参数是指要开始的地方，第二个参数是指每页显示多少条数据；
    public LemonContentQuery(Integer index, Integer size){
        this.offset = index * size - 1;
        this.size = size;

    }
    public LemonContentQuery(Long userId){
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}
