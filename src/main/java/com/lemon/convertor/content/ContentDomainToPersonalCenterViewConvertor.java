package com.lemon.convertor.content;

import com.alibaba.fastjson.JSONArray;
import com.lemon.convertor.ConvertorResult;
import com.lemon.domain.impl.content.Content;
import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.enums.ContentThingsStatusEnum;
import com.lemon.utils.DateUtils;
import com.lemon.view.lemon.contents.PersonalCenterContentsView;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/18.
 */
@Component("ContentDomainToPersonalCenterView_Convertor")
public class ContentDomainToPersonalCenterViewConvertor implements ConvertorResult<PersonalCenterContentsView>{
    @Override
    public PersonalCenterContentsView getResult(Object... obj) {
        Content content = (Content) obj[0];
        ContentPlan contentPlan = (ContentPlan) obj[1];

        PersonalCenterContentsView view = new PersonalCenterContentsView();

        view.setId(content.getId());
        view.setCreateTime(DateUtils.yearMonthDay(content.getCreatedTime()));
        view.setTitle(content.getTitle());
        view.setDescription(content.getDescription());
        List<String> images = (List<String>) JSONArray.parse(content.getImages());
        if (images != null && !images.isEmpty()) {
            view.setImageUrl(images);
        }
        view.setType(content.getType().getValue());

        if (contentPlan.getFinishedTime() != null && contentPlan.getFinished()) {
            view.setFinishedTime(DateUtils.yearMonthDay(contentPlan.getFinishedTime()));
        }
        // 过期时间在当前时间之后，还没有到期的内容
        if (contentPlan.getExpectTime().compareTo(LocalDateTime.now()) > 0) {
            Long time = Timestamp.valueOf(contentPlan.getExpectTime()).getTime() - Timestamp.valueOf(LocalDateTime.now()).getTime();
            Long days = time / (1000L * 60 * 60 * 24);
            if (days >= 0) {
                view.setLeftTime(days.intValue());
            }
        }
        // 设置状态
        // 如果是完成状态就是   ----> 完成
        // 如果当前时间大于了计划的时间限制且不是出于完成的状态 ---> 失败
        // 如果当前时间小于计划时间限制 ------> 正在进行
        // 如果是正在进行状态且剩余时间少于2天了 -----> 即将过期
        //
        if (contentPlan.getFinished()){
            view.setStatus(ContentThingsStatusEnum.FINISHED.name());
        }else if (LocalDateTime.now().compareTo(contentPlan.getExpectTime())>0 && !contentPlan.getFinished()) {
            view.setStatus(ContentThingsStatusEnum.FAILED.name());
        }else if (!contentPlan.getFinished() && LocalDateTime.now().compareTo(contentPlan.getExpectTime())<0){
            view.setStatus(ContentThingsStatusEnum.DOING.name());
            if (view.getLeftTime()<2){
               view.setStatus(ContentThingsStatusEnum.WILLEXPIRE.name());
            }
        }
        return view;
    }
}
