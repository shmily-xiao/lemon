package com.lemon.domain.impl.content;

import com.lemon.domain.impl.BaseDomain;
import com.lemon.domain.interfaces.content.IContent;
import com.lemon.enums.ContentType;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/7/27.
 */
public class Content extends BaseDomain implements IContent {

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

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public String getImages() {
        return images;
    }

    @Override
    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public ContentType getType() {
        return type;
    }

    @Override
    public void setType(ContentType type) {
        this.type = type;
    }

    @Override
    public Boolean getDel() {
        return del;
    }

    @Override
    public void setDel(Boolean del) {
        this.del = del;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }
}
