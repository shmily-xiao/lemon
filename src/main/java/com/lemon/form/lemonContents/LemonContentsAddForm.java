package com.lemon.form.lemonContents;

import com.lemon.enums.ContentType;
import com.lemon.enums.StrategyType;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/6.
 */
public class LemonContentsAddForm {

    // 标题
    @NotBlank
    private String title;
    // 内容
    @NotBlank
    private String content;
    // 完成的时间
    private String finishedTime;
    // 内容的类型
    private ContentType contentsType;
    // 是否需要短信提醒
    private Boolean needMessage = Boolean.FALSE;
    //访问控制
    private StrategyType strategyType;
    // 照片
    private List<String> images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }

    public ContentType getContentsType() {
        return contentsType;
    }

    public void setContentsType(ContentType contentsType) {
        this.contentsType = contentsType;
    }

    public Boolean getNeedMessage() {
        return needMessage;
    }

    public void setNeedMessage(Boolean needMessage) {
        this.needMessage = needMessage;
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(StrategyType strategyType) {
        this.strategyType = strategyType;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
