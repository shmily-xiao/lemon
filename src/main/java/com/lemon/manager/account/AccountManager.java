package com.lemon.manager.account;

import com.lemon.domain.impl.Cookies;
import com.lemon.domain.impl.user.User;
import com.lemon.service.IUserAccountService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by simpletour_Jenkin on 2016/12/27.
 */
@Component
public class AccountManager {

    private static final int COOKIES_LIFE_TIME = 60*30;

    /**
     * 设置cookie的生存时间*
     * @param session
     * @param response
     * @param user
     * @return
     */
    public Cookies createCookies(HttpSession session, HttpServletResponse response, User user ){
        // cookies 的存活时间
        int cookieTime = COOKIES_LIFE_TIME;
        String sessionId = session.getId();
        Cookie cookie = new Cookie("JSESSIONID",sessionId);//注意key值必须和原来一样，否则服务器无法标识用户
        Cookie cookie1 = new Cookie("userId",user.getId()+"");
        Cookie cookie2 = new Cookie("password",user.getPassword());
        cookie2.setMaxAge(cookieTime);
        cookie2.setPath("/");
        cookie1.setMaxAge(cookieTime);
        cookie1.setPath("/");
        cookie.setMaxAge(cookieTime);// 设置cookie的生命周期1800s  现在是30s
        cookie.setPath("/");  // 设置cookie有效路径，
        response.addCookie(cookie2);
        response.addCookie(cookie1);
        response.addCookie(cookie);//cookie添加完毕
        Cookies cookies = new Cookies(); //在数据库中储存每个id对应一个sessionId
        cookies.setUserId(user.getId()); //设置用户ID
        cookies.setSessionId(sessionId);   //实体里面有一个变量叫做sessionId
        Date now = new Date();
        Timestamp time = new Timestamp(now.getTime());
        cookies.setLoginTime(time);   //设置登陆时间
        cookies.setLifeTime(cookieTime);   //设置生命周期
        return cookies;
    }
}
