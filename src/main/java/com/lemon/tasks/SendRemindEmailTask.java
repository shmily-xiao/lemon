package com.lemon.tasks;

import com.alibaba.fastjson.JSON;
import com.lemon.domain.impl.content.Content;
import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.domain.impl.user.User;
import com.lemon.manager.email.EmailManager;
import com.lemon.pojo.mq.MQ;
import com.lemon.query.content.ContentPlanQuery;
import com.lemon.query.user.UserQuery;
import com.lemon.rocketmq.Producer;
import com.lemon.rocketmq.bo.MessageBO;
import com.lemon.service.IContentPlanService;
import com.lemon.service.IContentService;
import com.lemon.service.IUserService;
import com.lemon.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by wangzaijun on 2017/7/4.
 */
@Component
public class SendRemindEmailTask {

    @Resource
    private IContentPlanService contentPlanService;

    @Resource
    private IUserService userService;

    @Resource
    private IContentService contentService;

    @Resource
    private Producer producer;

    @Value("${mq.name.server}")
    private String nameServer;

    // 每天的0点运行
    @Scheduled(cron="0 0 0 * * *")
    public void sendDreamEmail(){
        ContentPlanQuery contentPlanQuery = new ContentPlanQuery();
        contentPlanQuery.setFinished(Boolean.FALSE);
        contentPlanQuery.setRemind(Boolean.TRUE);
        List<ContentPlan> contentPlans = contentPlanService.findList(contentPlanQuery);
        contentPlans.stream()
                // 保留还没有过期的
                .filter(contentPlan -> contentPlan.getExpectTime().isAfter(LocalDateTime.now()))
                // 保留还有1天过期的
                .filter(contentPlan -> contentPlan.getExpectTime().isBefore(LocalDateTime.now().plusDays(1)))
                .forEach(contentPlan -> {
                    Optional<User> userOptional = userService.find(contentPlan.getUserId());
                    if (userOptional.isPresent() && StringUtils.notEmpty(userOptional.get().getEmail())){
                        Optional<Content> contentOptional = contentService.find(contentPlan.getContentId());
                        if (contentOptional.isPresent()) {
                            String producerGroupName = "send_dream_email";
                            String topic = MQ.SEND_EMAIL_TOPIC;
                            String tags = MQ.DREAM_EMAIL_TAG;
                            MessageBO messageBO = new MessageBO(userOptional.get().getEmail(), contentOptional.get().getTitle());
                            String message = JSON.toJSONString(messageBO);
                            try {
                                producer.sendMessage(nameServer, producerGroupName, topic, tags, message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    // 每天的早上七点执行
    @Scheduled(cron = "0 0 7 * * *")
    public void sendBirthdayEmail(){
        UserQuery userQuery = new UserQuery();
        userQuery.setBirthday(LocalDate.now());
        List<User> userList = userService.findList(userQuery);
        userList.stream()
                .filter(user -> StringUtils.notEmpty(user.getEmail()) && user.getBirthday() != null)
                .forEach(
                user -> {
                    String producerGroupName = "send_birthday_email";
                    String topic = MQ.SEND_EMAIL_TOPIC;
                    String tags = MQ.BIRTHDAY_EMAIL_TAG;
                    MessageBO messageBO = new MessageBO(user.getEmail(), "birthday");
                    String message = JSON.toJSONString(messageBO);
                    try {
                        producer.sendMessage(nameServer, producerGroupName, topic, tags, message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

}
