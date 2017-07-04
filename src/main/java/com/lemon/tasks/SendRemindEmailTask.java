package com.lemon.tasks;

import com.lemon.domain.impl.content.ContentPlan;
import com.lemon.domain.impl.user.User;
import com.lemon.manager.email.EmailManager;
import com.lemon.query.content.ContentPlanQuery;
import com.lemon.service.IContentPlanService;
import com.lemon.service.IUserService;
import com.lemon.utils.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
    private EmailManager emailManager;

    // 每天的0点运行
    @Scheduled(cron="0 0 0 * * *")
    public void sendEmail(){
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
                        String subject = "小懒萌管家提醒";
                        String content = "您计划的任务还有一天就到期了，您是否已经完成了呢？回来完成它吧，小懒萌想你了。";
                        String displayName = "www.lemon-xiao.xin";
                        String receiveUser = userOptional.get().getEmail();
                        emailManager.sendRemindEmail2User(subject, content, displayName, receiveUser);
                    }
                });

    }

}
