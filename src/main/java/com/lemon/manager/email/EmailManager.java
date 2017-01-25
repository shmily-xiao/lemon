package com.lemon.manager.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Administrator on 2017/1/25 0025.
 */
@Component
public class EmailManager {

    @Value("${email.account}")
    private String emailAccount;

    @Value("${email.password}")
    private String emailPassword;


    /**
     * 发送邮件
     * @param content
     */
    public void sendFeedbackMail(String content, String account)
    {
        Properties props = new Properties();

        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        props.setProperty("mail.smtp.socketFactory.class", "javax.NET.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        Session session = Session.getInstance(props,null);
        session.setDebug(true);//打开debug模式，会打印发送细节到console
        Message message = new MimeMessage(session); //实例化一个MimeMessage集成自abstract Message 。参数为session
        try
        {
            message.setFrom(new InternetAddress("1350468513@qq.com")); //设置发出方,使用setXXX设置单用户，使用addXXX添加InternetAddress[]

            message.setText(content); //设置文本内容 单一文本使用setText,Multipart复杂对象使用setContent

            message.setSubject("账号为："+account+"用户的反馈"); //设置标题

            message.setRecipient(Message.RecipientType.TO, new InternetAddress("wangzaijun1234@126.com")); //设置接收方

            /**
             *使用静态方法每次发送需要建立一个到smtp服务器的链接，你可以手动控制连接状态 ，通过session获得tansport，连接到mailserver，而session就可以使用Session.getDefaultInstance(props,null);获得     */
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.qq.com",emailAccount,emailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch(AddressException e)
        {
            //此处处理AddressException异常  [The exception thrown when a wrongly formatted address is encountered.]
            e.printStackTrace();
        }catch(MessagingException e)
        {
            //此处处理MessagingException异常 [The base class for all exceptions thrown by the Messaging classes ]
            e.printStackTrace();
        }

    }
}
