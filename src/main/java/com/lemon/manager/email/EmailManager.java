package com.lemon.manager.email;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by Administrator on 2017/1/25 0025.
 */
@Component
public class EmailManager {
    @Resource
    private JavaMailSender javaMailSender;

    public void sendEmail(String msg,String account){
        String emails = "981189841@qq.com";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("账号为："+account+"用户的反馈");
        simpleMailMessage.setFrom("1350468513@qq.com");
        simpleMailMessage.setText(msg);
        simpleMailMessage.setTo(emails.split(","));
        javaMailSender.send(simpleMailMessage);

    }
}
