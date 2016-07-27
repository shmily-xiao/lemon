package com.demo.filter;

import com.demo.domain.Cookies;
import com.demo.domain.User;
import com.demo.service.CookiesService;
import com.demo.service.UserService;
import com.demo.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by simpletour_java on 2015/6/3.
 */
public class CookieFilter implements HandlerInterceptor{

    @Autowired  //注入
    CookiesService cookiesService;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        //2.如果用户没有登陆，再判断用户是否带登陆cookie来
        Cookie cookies[] = request.getCookies();
        Cookie autoCookie = null;
        Cookie autoName = null;
        Cookie autoPassword = null;
        if(cookies == null){
            return true;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("userId")){ //查找名为自动登录的cookie
                autoName = cookie;
            }
            if(cookie.getName().equals("JSESSIONID")){ //查找名为自动登录的cookie
                autoCookie = cookie;
            }
            if(cookie.getName().equals("password")){ //查找名为自动登录的cookie
                autoPassword = cookie;
            }
        }
        //3.如果用户没有带登陆cookie来，则直接放行
        if(autoCookie==null||autoName==null||autoPassword==null){
            return true;
        }
        //4.如果用户带了，则获取cookie的值
        String valuesS[] = autoCookie.getValue().split("\\:");
        String valuesU[] = autoName.getValue().split("\\:");
        String valuesP[] = autoPassword.getValue().split("\\:");

        Cookies cookies1 = cookiesService.findCookies(valuesU[0]);
        if(cookies1!=null){
            int lifeTime = cookies1.getLifeTime();
            Date loginTime = new Date(cookies1.getLoginTime().getTime());
            Long now = System.currentTimeMillis();
            //  5.从值里面得到cookie有效期，判断cookie是否有效
            if( (now-loginTime.getTime())>(lifeTime*1000) ||
                    !valuesS[0].equals(cookies1.getSessionId()) ){
                    //使用时间和sessionId进行比较然后登陆
                //6.如果cookie无效，则直接放行
                return true;
            }else{
                //7.如果cookie 有效，则用cookie里的用户名和密码信息为用户自动登陆
                //提取数据库的内容
                User user = userService.findUserName(valuesU[0]);
                if(user!=null){
                    int salt = user.getSalt();
                    String pwd = valuesP[0] + salt;
                    String password = Md5.messageDigest(pwd);
                    if(password.equals(user.getPassword())){
                        //如果匹配，则帮用户完成登陆
                        session.setAttribute("userId",user.getId());
                        session.setAttribute("userName",user.getName());
                        return true;
                    }
                }
            }
        }else{
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
