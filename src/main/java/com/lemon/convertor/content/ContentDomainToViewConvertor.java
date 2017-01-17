package com.lemon.convertor.content;

import com.alibaba.fastjson.JSON;
import com.lemon.convertor.ConvertorResult;
import com.lemon.domain.impl.content.Content;
import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.domain.impl.content.Interaction;
import com.lemon.enums.InteractionType;
import com.lemon.utils.DateUtils;
import com.lemon.view.lemon.contents.LemonContentsElementView;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by simpletour_Jenkin on 2017/1/16.
 */
@Component("ContentDomainToView_Convertor")
public class ContentDomainToViewConvertor implements ConvertorResult<LemonContentsElementView>{
    @Override
    public LemonContentsElementView getResult(Object... obj) {
        Content content = (Content) obj[0];
        ContentPlan contentPlan = (ContentPlan) obj[1];
        List<Interaction> interactions = (List<Interaction>) obj[2];

        LemonContentsElementView view = new LemonContentsElementView();
        // 内容
        if (content!=null) {
            view.setTitle(content.getTitle());
            view.setDescription(content.getDescription());
            List<String> images = (List<String>) JSON.parse(content.getImages());
            if (images != null && images.isEmpty()) {
                view.setImageUrl(images);
            }
            view.setUserId(content.getUserId());
            view.setCreateTime(DateUtils.yearMonthDayTime(content.getCreatedTime()));
        }

        // 时间安排
        if (contentPlan!=null) {
            if (contentPlan.getFinishedTime() != null) {
                view.setFinishedTime(DateUtils.yearMonthDayTime(contentPlan.getFinishedTime()));
            }
            if (contentPlan.getExpectTime().compareTo(LocalDateTime.now()) > 0) {
                Long time = Timestamp.valueOf(contentPlan.getExpectTime()).getTime() - Timestamp.valueOf(LocalDateTime.now()).getTime();
                Long days = time / 1000L * 60 * 60 * 24;
                view.setLeftTime(days.intValue());
            }
        }

        // 点赞的记录
        if (interactions!=null && !interactions.isEmpty()) {
            Map<InteractionType, List<Interaction>> typeListMap = interactions.stream()
                    .collect(Collectors.groupingBy(Interaction::getAction));
            view.setLikeCount(Long.valueOf(typeListMap.get(InteractionType.LIKE).size() + ""));
            view.setCollectCount(Long.valueOf(typeListMap.get(InteractionType.COLLECT).size() + ""));
        }

        // 用户的分组信息 2017-1-17保留 todo

        return view;
    }


}
