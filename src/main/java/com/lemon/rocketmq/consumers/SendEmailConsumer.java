package com.lemon.rocketmq.consumers;

import com.alibaba.fastjson.JSON;
import com.lemon.manager.email.EmailManager;
import com.lemon.pojo.mq.MQ;
import com.lemon.rocketmq.bo.MessageBO;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by wangzaijun on 2017/7/14.
 */
@Singleton
@Component
public class SendEmailConsumer implements InitializingBean {

    @Value("${mq.name.server}")
    private String nameServer;

    @Resource
    private EmailManager emailManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        DefaultMQPushConsumer consumer= new DefaultMQPushConsumer("sendDreamEmail" + MQ.CONSUMER_POSTFIX);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(MQ.SEND_EMAIL_TOPIC, MQ.DREAM_EMAIL_TAG + " || " + MQ.BIRTHDAY_EMAIL_TAG + " || " + MQ.FORGET_PWD_EMAIL_TAG);
        consumer.setNamesrvAddr(this.nameServer);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                MessageExt msg = msgs.get(0);
                if (MQ.SEND_EMAIL_TOPIC.equals(msg.getTopic())){
                    MessageBO messageBO;
                    try {
                        String json = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        messageBO = JSON.parseObject(json, MessageBO.class);
                    }catch (Exception e){
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }

                    String subject = "";
                    String content = "";
                    String displayName = "www.lemon-xiao.xin";

                    if (MQ.DREAM_EMAIL_TAG.equals(msg.getTags())){
                        subject = "小懒萌管家提醒";
                        content = emailManager.getContent(messageBO.getTheme());

                    } else if (MQ.BIRTHDAY_EMAIL_TAG.equals(msg.getTags())){
                        subject = "小懒萌的生日祝福";
                        content = emailManager.getBirthdayContent();

                    } else if (MQ.FORGET_PWD_EMAIL_TAG.equals(msg.getTags())){
                        subject = "小懒萌发来的验证码";
                        content = emailManager.getAuthCodeContent(messageBO.getEmail(), messageBO.getTheme());

                    }
                    emailManager.sendRemindEmail2User(subject, content, displayName, messageBO.getEmail());

                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("Consumer is started");
    }
}
