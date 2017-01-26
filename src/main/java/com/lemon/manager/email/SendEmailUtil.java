package com.lemon.manager.email;

import com.sun.mail.util.MailSSLSocketFactory;
import me.chanjar.weixin.common.util.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by simpletour_Jenkin on 2017/1/26.
 */
public class SendEmailUtil {


    private MimeMessage message;

    private String mailHost = "";
    private String mailPort = "";
    private String mailProtocol = "";
    private String sender_username = "";
    private String sender_password = "";

    private Properties properties = new Properties();

    /*
     * 初始化方法
     */
    public SendEmailUtil(boolean debug) {
        InputStream in = ClassLoader
                .getSystemResourceAsStream("email.properties");
        try {
            properties.load(in);
            this.mailHost = properties.getProperty("mail.smtp.host");
            this.mailPort = properties.getProperty("mail.smtp.port");
            this.mailProtocol = properties.getProperty("mail.smtp.protocol");
            this.sender_username = properties
                    .getProperty("mail.sender.username");
            this.sender_password = properties
                    .getProperty("mail.sender.password");
        } catch (IOException e) {
            System.out.println("读取默认参数文件异常，请重试!");

        }

    }

    /**
     * 用户名密码验证，需要实现抽象类Authenticator的抽象方法PasswordAuthentication，
     * SMTP验证类(内部类)，继承javax.mail.Authenticator
     */
    class MyAuthenricator extends Authenticator {
        String username = null;
        String password = null;

        public MyAuthenricator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    /**
     * 指定发送邮件
     *
     * @param subject
     *            邮件主题
     * @param sendHtml
     *            邮件内容
     * @param sender_nickName
     *            发送邮件人地址
     * @param receiveUser
     *            收件人列表，以","分割
     * @param filePath
     *            附件列表
     */
    public boolean sendEmail(String subject, String sender_nickName,
                             String sendHtml, String receiveUser) {
        Properties prop = new Properties();
        // 协议
        prop.setProperty("mail.transport.protocol", mailProtocol);
        // 服务器
        prop.setProperty("mail.smtp.host", mailHost);
        // 端口
        prop.setProperty("mail.smtp.port", mailPort);
        // 使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 使用SSL，企业邮箱必需！
        // 开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            System.out.println("开启SSL加密异常！");
            e1.printStackTrace();
            return false;
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getDefaultInstance(prop, new MyAuthenricator(
                sender_username, sender_password));
        session.setDebug(true);// 开启DEBUG模式,在控制台中或日志中有日志信息显示，也就是可以从控制台中看一下服务器的响应信息；
        message = new MimeMessage(session);
        try {
            InternetAddress from;

            if (StringUtils.isNotBlank(sender_nickName)) {
                // 发件人,昵称
                from = new InternetAddress(
                        MimeUtility.encodeWord(sender_nickName) + "<"
                                + sender_username + ">");
            } else {
                // 发件人
                from = new InternetAddress(
                        MimeUtility.encodeWord(sender_nickName));
            }
            message.setFrom(from);

            // 创建收件人列表
            if (StringUtils.isNotBlank(receiveUser)) {
                // 替换收件人的分隔符（防止中文下产生无法分割成收件人数组的'，'替换成','）
                String rescivers = receiveUser.replaceAll("，", ",");
                String[] arr = rescivers.split(",");
                if (arr.length > 0) {
                    // 收件人
                    //采用Address【】比InternetAddress发送邮件速度更快 因为InternetAddress是继承与Address，
                    Address[] address=new Address[arr.length];
                    //InternetAddress[] address = new InternetAddress[arr.length];
                    for (int i = 0; i < arr.length; i++) {
                        address[i] = new InternetAddress(arr[i]);
                    }
                    message.setRecipients(Message.RecipientType.TO, address);

                    // 发送时间
                    message.setSentDate(new Date());

                    // 邮件主题
                    message.setSubject(subject);

                    // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
                    Multipart multipart = new MimeMultipart();

                    // 添加邮件正文
                    BodyPart contentPart = new MimeBodyPart();
                    contentPart.setContent(sendHtml, "text/html;charset=UTF-8");

                    multipart.addBodyPart(contentPart);

//                    // 附件操作
//                    if (filePath != null && filePath.size() > 0) {
//                        for (String filename : filePath) {
//                            BodyPart attachmentBodyPart = new MimeBodyPart();
//                            DataSource source = new FileDataSource(filename);
//                            attachmentBodyPart.setDataHandler(new DataHandler(
//                                    source));
//                            // MimeUtility.encodeWord可以避免文件名乱码
//                            attachmentBodyPart.setFileName(MimeUtility
//                                    .encodeWord(source.getName()));
//                            multipart.addBodyPart(attachmentBodyPart);
//                        }
//                        // 移走集合中的所有元素
//                        filePath.clear();
//                    }
                    // 将multipart对象放到message中
                    message.setContent(multipart);

                    // 保存邮件
                    message.saveChanges();
                    // 不采用ssl加密发送
                    // transport = session.getTransport("smtp");
                    // smtp验证，就是你用来发邮件的邮箱用户名密码
                    // transport.connect(mailHost, sender_username,
                    // sender_password);
                    // 发送
                    // transport.sendMessage(message,
                    // message.getAllRecipients());

                    // 发送
                    Transport.send(message,address);
                    System.out.println("send success!");
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("message异常！");
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {

//        String subject = "测试";
//        String nickName = "风中思絮";
//        String sendHtml = "<p class='color:red'>由于下雨，周末不加班！</p>";
//
//        String receiveUser = "wangzaijun1234@126.com";
////      String receiveUser = "xxx@qq.com";
//
////        List<String> filePath = new ArrayList();
////      filePath.add("D:\\JAVAStudy\\bpm学习\\SVN地址.txt");
////        filePath.add("D:\\JAVAStudy\\学习进度\\学习目标及目标.txt");
////      filePath.add("D:\\JAVAStudy\\bpm学习\\BPM配置文档(1).docx");
////        filePath.add("D:\\JAVAStudy\\bpm学习\\OA\\任务分配.png");
////      filePath.add("D:\\JAVAStudy\\微信支付.rar");
//
//        SendEmailUtil sendEmail = new SendEmailUtil(true);
//        long time1=System.currentTimeMillis();
//        boolean  isSend = sendEmail.sendEmail(subject, nickName, sendHtml, receiveUser);
//        long time2=System.currentTimeMillis();
//        System.err.println("sendEmail spend time:"+(time2-time1));


    }



}

