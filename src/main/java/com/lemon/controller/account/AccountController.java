package com.lemon.controller.account;

import com.lemon.domain.Cookies;
import com.lemon.domain.User;
import com.lemon.form.AjaxResponse;
import com.lemon.form.user.UserAccountForm;
import com.lemon.query.user.UserQuery;
import com.lemon.service.ICookiesService;
import com.lemon.service.IUserService;
import com.lemon.utils.Md5;
import com.lemon.utils.SequenceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

/**
 * Created by simpletour_java on 2015/6/3.
 */
@Controller
public class AccountController {

    @Resource //  但是@Autowired 是spring提供的，@Resource是j2ee提供的
    private IUserService userService;

    @Resource
    private ICookiesService cookiesService;

    @RequestMapping(value = "/account/register")  //默认为GET
    public String register(){
        return "/lemon/account/register";
    }


    @ResponseBody
    @RequestMapping(value = "/account/register",method = RequestMethod.POST)
    public AjaxResponse register(@Valid UserAccountForm userForm, BindingResult result, HttpServletRequest request, HttpServletResponse response){
        if (result.hasErrors()){
            return AjaxResponse.fail().msg("注册失败").reason("用户没有提交数据");
        }

        Optional<User> userOptional = userService.findOne(new UserQuery(userForm.getMobile()));
        if (userOptional.isPresent()) {
            return AjaxResponse.fail().msg("注册失败").reason("该手机号已经注册过了");
        }

        String salt = SequenceUtils.generateAlphaNun(5); //生成salt
        String password = Md5.messageDigest(userForm.getPassword() + salt);
        User user = new User(userForm.getMobile(),userForm.getMobile(),password,salt);

        HttpSession session = request.getSession();
        cookiesService.insertCookies(this.createCookies(session,response,user));//登陆成功后就会抛出用户ID和用户名

        session.setAttribute("user.nickname", user.getNickName());

        //todo 首页，或者是个人中心 url
        return AjaxResponse.ok().url("");
    }

    /**
     * 设置cookie的生存时间*
     * @param session
     * @param response
     * @param user
     * @return
     */
    private Cookies createCookies(HttpSession session, HttpServletResponse response, User user ){
        // cookies 的存活时间
        int cookieTime = 60*30;
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



    @RequestMapping(value = "/account/login")  //默认是GET方法
    public String login(){
        return "/lemon/account/login";
    }

//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public AjaxResponse login(User user,HttpServletRequest request,HttpServletResponse response) {
//
//    }
////
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String login(User user,Model model,HttpServletRequest request,HttpServletResponse response){
////        System.out.println("login: " + user.getPassword());
//        if(user.getId()!=null && !user.getId().equals("")
//                && user.getPassword()!= null && !user.getPassword().equals("")){
//            User userTemp = userService.findUserName(user.getId());
//            if(userTemp != null){
//                int salt = userTemp.getSalt();  //从数据库获取salt
//                String pwdTemp = user.getPassword();
//                String pwd = user.getPassword() + salt;  //组合
//                String password = Md5.messageDigest(pwd);  //生成MD5摘要
//                user.setPassword(password);
//                if(userTemp.getPassword().equals(password)){
//                    int cookieTime = 60*30; //生存的时间 以秒为单位
//                    HttpSession session = request.getSession();
//                    String sessionId = session.getId();
//                    Cookie cookie = new Cookie("JSESSIONID",sessionId);//注意key值必须和原来一样，否则服务器无法标识用户
//                    Cookie cookie1 = new Cookie("userId",user.getId());
//                    Cookie cookie2 = new Cookie("password",pwdTemp);
//                    cookie.setMaxAge(cookieTime);// 设置cookie的生命周期1800s
//                    cookie1.setMaxAge(cookieTime);
//                    cookie2.setMaxAge(cookieTime);
//                    cookie.setPath("/");  // 设置cookie有效路径，
//                    cookie1.setPath("/");
//                    cookie2.setPath("/");
//                    response.addCookie(cookie2);
//                    response.addCookie(cookie1);
//                    response.addCookie(cookie);//cookie添加完毕
//                    Cookies cookies = new Cookies(); //在数据库中储存每个id对应一个sessionId
//                    cookies.setId(user.getId());  //设置用户ＩＤ
//                    cookies.setSessionId(sessionId);   //实体里面有一个变量叫做sessionId
//                    Date now = new Date();
//                    Timestamp time = new Timestamp(now.getTime());
//                    cookies.setLoginTime(time);   //设置登陆时间
//                    cookies.setLifeTime(cookieTime);   //设置生命周期
////                    System.out.println("login:"+user.getId()+"==="+sessionId);
//                    ICookiesService.updateCookies(cookies);  //更新
////                    System.out.println("login:" + sessionId);
//                    session.setAttribute("userId",user.getId());
//                    session.setAttribute("userName", userTemp.getName());
//                    return "success";
//                }else{
//                    model.addAttribute("message","用户ID或者密码错误！");
//                    return "login";
//                }
//
//            }else{
//                model.addAttribute("message","此用户ID不存在！");
//                return "login";
//            }
//
//        }else{
//            model.addAttribute("message","必须所有的都填写！");
//            return "login";
//        }
//    }

}
