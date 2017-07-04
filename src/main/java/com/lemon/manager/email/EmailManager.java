package com.lemon.manager.email;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/25 0025.
 */
@Component
public class EmailManager {
//    @Resource
//    private JavaMailSender javaMailSender;

    @Resource
    private SendEmailUtil sendEmailUtil;

    /**
     *
     * @param msg
     * @param account
     */
    public void sendEmail(String msg,String account){
//        String emails = "wangzaijun1234@126.com";
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setSubject("账号为："+account+"用户的反馈");
//        simpleMailMessage.setFrom("1350468513@qq.com");
//        simpleMailMessage.setText(msg);
//        simpleMailMessage.setTo(emails.split(","));
//        sendEmailUtil.send(simpleMailMessage);
        sendEmailUtil.sendEmail(account,msg);

    }

    /**
     *
     * @param subject  主题
     * @param content  内容
     * @param displayName  显示名字
     * @param receiveUser  接收人
     */
    public void sendRemindEmail2User(String subject, String content, String displayName, String receiveUser){
        sendEmailUtil.sendEmail(subject, displayName, content, receiveUser);
    }

}
