package com.demo.controller;

import com.demo.dao.CookiesDao;
import com.demo.domain.Cookies;
import com.demo.domain.User;
import com.demo.service.CookiesService;
import com.demo.service.UserService;
import com.demo.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

/**
 * Created by simpletour_java on 2015/6/3.
 */
@Controller
public class RegisterController {

    @Autowired //和@Resource差不多  但是前者是spring提供的，后者是j2ee提供的
    private UserService userService;

    @Autowired
    private CookiesService cookiesService;

    @RequestMapping(value = "/register")  //默认为GET
    public String register(){
        return "register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(User user,Model model,HttpServletResponse response,HttpServletRequest request){
//        System.out.println(user.getId() + " " + user.getName() + " " + user.getPassword());
//        System.out.println("register: "+user.getPassword());
        if(user.getId()!=null && !user.getId().equals("")
                && user.getName()!=null && !user.getName().equals("")
                && user.getPassword()!=null && !user.getPassword().equals("")){
            int count = userService.findName(user.getId());
            if(count!=0){
                model.addAttribute("message","此用户ID已经被注册！");
                return "register";
            }else{
                int salt = (int)(((Math.random()*100+1)*1000)-1); //生成salt 999--99999
                user.setSalt(salt);
                String pwd = user.getPassword()+salt;   //组合
                String password = Md5.messageDigest(pwd);  //组合之后再生成MD5摘要信息
                user.setPassword(password);
                userService.insert(user);
                int cookieTime = 60*30;
                HttpSession session = request.getSession();
                String sessionId = session.getId();
                Cookie cookie = new Cookie("JSESSIONID",sessionId);//注意key值必须和原来一样，否则服务器无法标识用户
                Cookie cookie1 = new Cookie("userId",user.getId());
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
                cookies.setId(user.getId()); //设置用户ID
                cookies.setSessionId(sessionId);   //实体里面有一个变量叫做sessionId
                Date now = new Date();
                Timestamp time = new Timestamp(now.getTime());
                cookies.setLoginTime(time);   //设置登陆时间
                cookies.setLifeTime(cookieTime);   //设置生命周期
//                System.out.println("register:" + user.getId() + "===" + sessionId);
                cookiesService.insertCookies(cookies);//登陆成功后就会抛出用户ID和用户名
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", user.getName());
//                System.out.println("register:" + sessionId);
                model.addAttribute("message","注册成功！");
                return "success";
            }
        }else{
            model.addAttribute("message","所有的内容都需要正确的填写！");
            return "register";
        }

    }

    @RequestMapping(value = "/login")  //默认是GET方法
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user,Model model,HttpServletRequest request,HttpServletResponse response){
//        System.out.println("login: " + user.getPassword());
        if(user.getId()!=null && !user.getId().equals("")
                && user.getPassword()!= null && !user.getPassword().equals("")){
            User userTemp = userService.findUserName(user.getId());
            if(userTemp != null){
                int salt = userTemp.getSalt();  //从数据库获取salt
                String pwdTemp = user.getPassword();
                String pwd = user.getPassword() + salt;  //组合
                String password = Md5.messageDigest(pwd);  //生成MD5摘要
                user.setPassword(password);
                if(userTemp.getPassword().equals(password)){
                    int cookieTime = 60*30; //生存的时间 以秒为单位
                    HttpSession session = request.getSession();
                    String sessionId = session.getId();
                    Cookie cookie = new Cookie("JSESSIONID",sessionId);//注意key值必须和原来一样，否则服务器无法标识用户
                    Cookie cookie1 = new Cookie("userId",user.getId());
                    Cookie cookie2 = new Cookie("password",pwdTemp);
                    cookie.setMaxAge(cookieTime);// 设置cookie的生命周期1800s
                    cookie1.setMaxAge(cookieTime);
                    cookie2.setMaxAge(cookieTime);
                    cookie.setPath("/");  // 设置cookie有效路径，
                    cookie1.setPath("/");
                    cookie2.setPath("/");
                    response.addCookie(cookie2);
                    response.addCookie(cookie1);
                    response.addCookie(cookie);//cookie添加完毕
                    Cookies cookies = new Cookies(); //在数据库中储存每个id对应一个sessionId
                    cookies.setId(user.getId());  //设置用户ＩＤ
                    cookies.setSessionId(sessionId);   //实体里面有一个变量叫做sessionId
                    Date now = new Date();
                    Timestamp time = new Timestamp(now.getTime());
                    cookies.setLoginTime(time);   //设置登陆时间
                    cookies.setLifeTime(cookieTime);   //设置生命周期
//                    System.out.println("login:"+user.getId()+"==="+sessionId);
                    cookiesService.updateCookies(cookies);  //更新
//                    System.out.println("login:" + sessionId);
                    session.setAttribute("userId",user.getId());
                    session.setAttribute("userName", userTemp.getName());
                    return "success";
                }else{
                    model.addAttribute("message","用户ID或者密码错误！");
                    return "login";
                }

            }else{
                model.addAttribute("message","此用户ID不存在！");
                return "login";
            }

        }else{
            model.addAttribute("message","必须所有的都填写！");
            return "login";
        }
    }

}
