package com.lemon.controller.account;

import com.alibaba.fastjson.JSON;
import com.lemon.controller.BaseController;
import com.lemon.domain.impl.Cookies;
import com.lemon.domain.impl.msm.MsmSendlog;
import com.lemon.domain.impl.user.User;
import com.lemon.enums.SignupType;
import com.lemon.form.AjaxResponse;
import com.lemon.form.user.UserAccountForm;
import com.lemon.manager.account.AccountManager;
import com.lemon.pojo.constants.LemonConstants;
import com.lemon.pojo.mq.MQ;
import com.lemon.rocketmq.Producer;
import com.lemon.rocketmq.bo.MessageBO;
import com.lemon.service.ICookiesService;
import com.lemon.service.IMsmSendlogService;
import com.lemon.service.IUserService;
import com.lemon.utils.Md5;
import com.lemon.utils.SequenceUtils;
import com.lemon.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by simpletour_java on 2015/6/3.
 */
@Controller
public class AccountController extends BaseController{

    //// TODO: 2016/8/8 0008 需要写一个拦截器，完成用户的自动登录

    @Resource //  但是@Autowired 是spring提供的，@Resource是j2ee提供的
    private IUserService userService;

    @Resource
    private ICookiesService cookiesService;

    @Resource
    private AccountManager accountManager;

    @Resource
    private Producer producer;

    @Resource
    private IMsmSendlogService msmSendlogService;


    @Value("${mq.name.server}")
    private String nameServer;


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

        // 使用手机号注册的时候会生成两个账号
        Optional<User> userOptional = userService.findUserByAccount(userForm.getMobile());
        if (userOptional.isPresent()) {
            return AjaxResponse.fail().msg("注册失败").reason("该手机号已经注册过了");
        }

        String salt = SequenceUtils.generateAlphaNun(5); //生成salt
        String password = Md5.messageDigest(userForm.getPassword() + salt);
        // 系统默认的账号
        String account = SequenceUtils.generateAlpha(4) + userForm.getMobile();
        // 昵称默认是 account
        User user = new User(account, account, password, salt, SignupType.MOBILE);

        Optional<User> newUser = userService.createUser(user, userForm.getMobile());
        if (!newUser.isPresent()){
            return AjaxResponse.fail().msg("注册失败").reason("网络异常请稍后再试");
        }

        HttpSession session = request.getSession();
        cookiesService.insertCookies(accountManager.createCookies(session,response,newUser.get()));//登陆成功后就会抛出用户ID和用户名

//        session.setAttribute("user.nickname", user.getNickName());
        session.setAttribute(LemonConstants.USER_SEESSION_ACCOUNT, user.getAccount());
        session.setAttribute(LemonConstants.USER_SEESSION_ID, user.getId());

        // 跳转首页（朋友圈）
        return AjaxResponse.ok().url("/lemon/lemons/friends");
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
     * 登录页面
     * @return
     */
//    @RequestMapping(value = "/lemon/account/qq/login")  //默认是GET方法
    public String qqLogin(){
        return "lemon/account/qqlogin";
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

        // 手机号和默认的系统账号都可以登录，使用qq登录之后也可以利用qq号登录
        Optional<User> userOptional = userService.findUserByAccount(userForm.getMobile());
        if (!userOptional.isPresent()){
            return AjaxResponse.fail().msg("登录失败").reason("用户不存在");
        }

        // 账号是否还是可用的状态
        if (!Boolean.TRUE.toString().equals(userOptional.get().getStatus())){
            return AjaxResponse.fail().msg("登录失败").reason("用户账号不可用,请与管理员联系");
        }

        User user = userOptional.get();

        String password = Md5.messageDigest(userForm.getPassword() + user.getSalt());
        if (!user.getPassword().equals(password)){
            return AjaxResponse.fail().msg("登录失败").reason("用户名或者密码错误");
        }

        HttpSession session = request.getSession();
        Cookies cookies = accountManager.createCookies(session, response, user);
        cookiesService.updateCookies(cookies);

//        session.setAttribute("user.nickname", user.getNickName());
        session.setAttribute(LemonConstants.USER_SEESSION_ACCOUNT, user.getAccount());
        session.setAttribute(LemonConstants.USER_SEESSION_ID, user.getId());

        //首页（朋友圈）
        return AjaxResponse.ok().url("/lemon/lemons/friends");

    }

    /**
     * 发送验证码到用户的邮箱中，改密码
     * @param account
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lemon/account/forget",method = RequestMethod.GET)
    public AjaxResponse sendAuthCodeEmail(@RequestParam String account, HttpServletRequest request){

        Optional<User> userOptional = userService.findUserByAccount(account);
        if (!userOptional.isPresent()){
            return AjaxResponse.fail().msg("您输入的"+account+"账户不存在哦！");
        }
        String email = userOptional.get().getEmail();
        if (StringUtils.isEmpty(email)){
            return AjaxResponse.fail().msg("您要找回的账户没有添加邮箱号码，无法使用密码找回功能!");
        }
        if (!"true".equals(userOptional.get().getStatus())){
            return AjaxResponse.fail().msg("您的账户存在问题哦，请联系管理员 wangzaijun1234@126.com");
        }
        // 检查记录是否有一条最新的日志
        Optional<MsmSendlog> sendlogOptional = msmSendlogService.getTheAuthCodeByMobile(email);
        if (sendlogOptional.isPresent()) {
            // 有效性为30分钟
            LocalDateTime oldTime = sendlogOptional.get().getCreatedTime().plusMinutes(30);
            if (oldTime.isAfter(LocalDateTime.now())){
                return AjaxResponse.fail().msg("验证码已经发送至您的邮箱请注意查收！");
            }
        }

        String producerGroupName = "send_forget_pwd_email";
        String topic = MQ.SEND_EMAIL_TOPIC;
        String tags = MQ.FORGET_PWD_EMAIL_TAG;
        MessageBO messageBO = new MessageBO(email, userOptional.get().getId().toString());
        String message = JSON.toJSONString(messageBO);
        try {
            producer.sendMessage(nameServer, producerGroupName, topic, tags, message);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResponse.fail().msg("网络异常，请联系管理员 wangzaijun1234@126.com");
        }
        return AjaxResponse.ok();
    }



}
