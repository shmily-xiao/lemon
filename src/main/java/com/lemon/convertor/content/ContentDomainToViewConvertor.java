package com.lemon.convertor.content;

import com.alibaba.fastjson.JSON;
import com.lemon.convertor.ConvertorResult;
import com.lemon.domain.impl.content.Content;
import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.utils.DateUtils;
import com.lemon.view.lemon.contents.LemonContentsElementView;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/16.
 */
@Component("ContentDomainToView_Convertor")
public class ContentDomainToViewConvertor implements ConvertorResult<LemonContentsElementView>{
    @Override
    public LemonContentsElementView getResult(Object... obj) {
        Content content = (Content) obj[0];
        ContentPlan contentPlan = (ContentPlan) obj[1];

        LemonContentsElementView view = new LemonContentsElementView();
        view.setTitle(content.getTitle());
        view.setDescription(content.getDescription());
        List<String> images = (List<String>)JSON.parse(content.getImages());
        if (images != null && images.isEmpty()) {
            view.setImageUrl(images);
        }
        view.setUserId(content.getUserId());
        if (contentPlan.getFinishedTime()!=null) {
            view.setFinishedTime(DateUtils.yearMonthDayTime(contentPlan.getFinishedTime()));
        }
        if (contentPlan.getExpectTime().compareTo(LocalDateTime.now()) > 0) {
            Timestamp.valueOf(contentPlan.getExpectTime()).getTime();

        }






        return null;
    }


}
