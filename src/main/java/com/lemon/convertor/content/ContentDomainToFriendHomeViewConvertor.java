package com.lemon.convertor.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lemon.convertor.ConvertorResult;
import com.lemon.domain.impl.content.Content;
import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.domain.impl.content.Interaction;
import com.lemon.domain.impl.user.User;
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
@Component("ContentDomainToFriendHomeView_Convertor")
public class ContentDomainToFriendHomeViewConvertor implements ConvertorResult<LemonContentsElementView>{
    @Override
    public LemonContentsElementView getResult(Object... obj) {
        Content content = null;
        if (obj[0]!=null) {
          content = (Content) obj[0];
        }
        ContentPlan contentPlan = null;
        if (obj[1]!=null){
            contentPlan = (ContentPlan) obj[1];
        }
        List<Interaction> interactions =null;
        if (obj[2]!=null) {
             interactions = (List<Interaction>) obj[2];
        }
        User user = null;
        if (obj[3]!=null) {
           user = (User) obj[3];
        }
        Long currentUserId = (Long) obj[4];

        LemonContentsElementView view = new LemonContentsElementView();

        // 用户信息
        if (user!=null){
            view.setAvatar(user.getAvatar());
            view.setNickName(user.getNickName());
        }

        // 内容
        if (content!=null) {
            view.setTitle(content.getTitle());
            view.setDescription(content.getDescription());
            List<String> images = (List<String>) JSONArray.parse(content.getImages());
            if (images != null && !images.isEmpty()) {
                view.setImageUrl(images);
            }
            view.setUserId(content.getUserId());
            view.setCreateTime(DateUtils.yearMonthDay(content.getCreatedTime()));
            view.setType(content.getType().getValue());
            view.setId(content.getId());
        }

        // 时间安排
        if (contentPlan!=null) {
            if (contentPlan.getFinishedTime() != null && contentPlan.getFinished()) {
                view.setFinishedTime(DateUtils.yearMonthDay(contentPlan.getFinishedTime()));
            } else if (contentPlan.getExpectTime().compareTo(LocalDateTime.now()) > 0) {
                Long time = Timestamp.valueOf(contentPlan.getExpectTime()).getTime() - Timestamp.valueOf(LocalDateTime.now()).getTime();
                Long days = time / (1000L * 60 * 60 * 24);
                if (days >= 0) {
                    view.setLeftTime(days.intValue());
                }
            }
        }

        // 点赞的记录
        if (interactions!=null && !interactions.isEmpty()) {
            Map<InteractionType, List<Interaction>> typeListMap = interactions.stream()
                    .collect(Collectors.groupingBy(Interaction::getAction));
            List<Interaction> interactionsLike = typeListMap.get(InteractionType.LIKE);
            List<Interaction> interactionsCollect = typeListMap.get(InteractionType.COLLECT);
            if (interactionsLike!=null && !interactionsLike.isEmpty()) {
                view.setLikeCount(Long.valueOf(interactionsLike.size() + ""));
            }
            if (interactionsCollect!=null && !interactionsCollect.isEmpty()) {
                view.setCollectCount(Long.valueOf(interactionsCollect.size() + ""));
            }
            if (user!=null) {
                view.setLikeStatus(this.isIncludeCurrentUserId(interactionsLike, currentUserId).toString());
                view.setCollectStatus(this.isIncludeCurrentUserId(interactionsCollect, currentUserId).toString());
            }
        }

        // 用户的分组信息 2017-1-17保留 todo

        return view;
    }

    private Boolean isIncludeCurrentUserId(List<Interaction> interactions, Long userId){
        if (interactions==null || interactions.isEmpty()) return Boolean.FALSE;
        return interactions.stream().anyMatch(interaction -> userId.equals(interaction.getUserId()));
    }

}
