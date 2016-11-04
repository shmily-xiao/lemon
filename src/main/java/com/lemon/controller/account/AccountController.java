package com.lemon.controller.account;

import com.lemon.domain.Cookies;
import com.lemon.domain.user.User;
import com.lemon.enums.SignupType;
import com.lemon.form.AjaxResponse;
import com.lemon.form.user.UserAccountForm;
import com.lemon.service.ICookiesService;
import com.lemon.service.IUserService;
import com.lemon.utils.Md5;
import com.lemon.utils.SequenceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
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

    //// TODO: 2016/8/8 0008 需要写一个拦截器，完成用户的自动登录
    
    @Resource //  但是@Autowired 是spring提供的，@Resource是j2ee提供的
    private IUserService userService;

    @Resource
    private ICookiesService cookiesService;


    private static final int COOKIES_LIFE_TIME = 60*30;

    /**
     * 注册页面
     * @return
     */
    @RequestMapping(value = "/lemon/account/register")  //默认为GET
    public String register(){
        return "/lemon/account/register";
    }


    /**
     * 手机号注册
     * @param userForm
     * @param result
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lemon/account/register",method = RequestMethod.POST)
    public AjaxResponse register(@Valid @RequestBody UserAccountForm userForm, BindingResult result, HttpServletRequest request, HttpServletResponse response){
        if (result.hasErrors()){
            return AjaxResponse.fail().msg("注册失败").reason("用户没有提交数据");
        }

        Optional<User> userOptional = userService.findUserByAccount(userForm.getMobile());
        if (userOptional.isPresent()) {
            return AjaxResponse.fail().msg("注册失败").reason("该手机号已经注册过了");
        }

        String salt = SequenceUtils.generateAlphaNun(5); //生成salt
        String password = Md5.messageDigest(userForm.getPassword() + salt);
        String account = SequenceUtils.generateAlpha(4) + userForm.getMobile();
        // 昵称默认是 account
        User user = new User(account, account, password, salt, SignupType.MOBILE);

        Optional<User> newUser = userService.createUser(user, userForm.getMobile());
        if (!newUser.isPresent()){
            return AjaxResponse.fail().msg("注册失败").reason("网络异常请稍后再试");
        }

        HttpSession session = request.getSession();
        cookiesService.insertCookies(this.createCookies(session,response,newUser.get()));//登陆成功后就会抛出用户ID和用户名

//        session.setAttribute("user.nickname", user.getNickName());
        session.setAttribute("user.account", user.getMobile());

        //todo 首页，或者是个人中心 url
        return AjaxResponse.ok().url("");
    }


    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/lemon/account/login")  //默认是GET方法
    public String login(){
        return "lemon/account/login";
    }

    /**
     * 登录
     * @param userForm
     * @param result
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lemon/account/login",method = RequestMethod.POST)
    public AjaxResponse login(@Valid @RequestBody UserAccountForm userForm,BindingResult result, HttpServletRequest request,HttpServletResponse response) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg("登录失败").reason("用户没有提交任何数据");
        }

        Optional<User> userOptional = userService.findUserByAccount(userForm.getMobile());
        if (!userOptional.isPresent()){
            return AjaxResponse.fail().msg("登录失败").reason("用户不存在");
        }

        if (!Boolean.TRUE.toString().equals(userOptional.get().getStatus())){
            return AjaxResponse.fail().msg("登录失败").reason("用户账号不可用,请与管理员联系");
        }

        User user = userOptional.get();

        String password = Md5.messageDigest(userForm.getPassword() + user.getSalt());
        if (!user.getPassword().equals(password)){
            return AjaxResponse.fail().msg("登录失败").reason("用户名或者密码错误");
        }

        HttpSession session = request.getSession();
        Cookies cookies = this.createCookies(session, response, user);
        cookiesService.updateCookies(cookies);

//        session.setAttribute("user.nickname", user.getNickName());
        session.setAttribute("user.account", user.getMobile());

        //// TODO: 2016/8/3 首页 url
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
