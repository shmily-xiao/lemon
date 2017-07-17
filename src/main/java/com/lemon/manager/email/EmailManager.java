package com.lemon.manager.email;

import com.lemon.enums.ConstellationEnums;
import com.lemon.extend.ConstellationAPI;
import com.lemon.utils.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Random;

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


    /**
     *  发送的模板
     * @param theme
     * @return
     */
    public String getContent(String theme){
        Random random = new Random();
        Integer integer = random.nextInt();
        Integer switchNum = integer % 6;
        switch (switchNum){
            case 0:
                return "您还记得您添加的 “"+ theme +"” 计划的任务么？还有一天就到期了哦~ \n 您是否已经完成了呢？快回来完成它吧，小懒萌想您了。\n www.lemon-xiao.xin";
            case 1:
                return "A long long time ago ~ 你添加了主题为 “"+ theme +"” 的任务。还有一天就到期了哦~ \n  是否已经完成了呢？ 你那么可爱，看见你小懒萌感觉自己已经恋爱了呢。\n www.lemon-xiao.xin";
            case 2:
                return "您计划的任务还有一天就到期了，您是否已经完成了呢？回来完成它吧，小懒萌想你了。 \n www.lemon-xiao.xin";
            case 3:
                return "小朋友，你是不是在偷懒呢？ 你主题为 " + theme + "的任务都还没有完成哦。你再不回来完成它，小懒萌以后就不理你了。哼~~ \n www.lemon-xiao.xin";
            case 4:
                return "你还记得你和小懒萌之间的约定么？你说你会完成 " + theme +" ，可是现在你是不是已经抛弃小懒萌了~，小懒萌还在那里等着你呢。\n www.lemon-xiao.xin";
            case 5:
                return  "啦啦啦~ 小懒萌出现啦。 我是来提醒你还有未完成的任务哦。 小懒萌最讨厌不守信用的人了~。你不听话，我就要把你按在凳子上“啪啪”打屁股。 \n www.lemon-xiao.xin";
        }
        return "啦啦啦~ 小懒萌出现啦。 我是来提醒你还有未完成的任务哦。 小懒萌最讨厌不守信用的人了~。你不听话，我就要把你按在凳子上“啪啪”打屁股。 \n www.lemon-xiao.xin";
    }

    public String getBirthdayContent(){
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        ConstellationEnums enums = ConstellationEnums.getEnums(month, day);
        String string = ConstellationAPI.getRequest1(enums.getName());
        if (StringUtils.isEmpty(string)){
            return "小懒萌等着一天好久了呢。生日快乐哦！。 \n 梦想一定要有哦，万一不小心实现了呢。";
        }
        JSONObject jsonObject = JSONObject.fromObject(string);
        String summary =  (String) jsonObject.get("summary");
        StringBuffer sb = new StringBuffer();
        sb.append("\n")
                .append("小懒萌听说你今天的幸运星座是")
                .append(jsonObject.get("QFriend"))
                .append("哦，快去把他（她）活捉过来为你庆祝生日吧。")
                .append("\n")
                .append("小懒萌还听说，你今天的幸运颜色是")
                .append(jsonObject.get("color"))
                .append("哦。")
                .append("\n")
                .append("小懒萌昨日夜观天象为主人祈福啦，求得一签，签上说： \n")
                .append("      ")
                .append(summary)
                .append("\n\n\n\n\n")
                .append("小懒萌等着一天好久了呢。小懒萌祝你生日快乐哦！")
                .append("\nwww.lemon-xiao.xin");
        return sb.toString();
    }

}
