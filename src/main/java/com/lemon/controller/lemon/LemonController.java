package com.lemon.controller.lemon;

import com.lemon.annotation.UserLoginValidation;
import com.lemon.controller.BaseController;
import com.lemon.form.AjaxResponse;
import com.lemon.manager.user.HeadUserInfoManager;
import com.lemon.view.user.HeadUserInfoView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by simpletour_Jenkin on 2016/8/10.
 * 首页，个人中心
 */
@Controller
public class LemonController extends BaseController{

//    @Resource
//    private IContentService lemonService;
//
    @Resource
    private HeadUserInfoManager headUserInfoManager;

    /**
     * 首页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/lemon/lemons")
    public String lemonsHome(HttpServletRequest request, Model model){

//        List<Content> lemons = lemonService.findByPage(new LemonQuery(1,10));

        return "lemon/home/home";
    }

    /**
     * 首页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/lemon/lemons/test")
    public String homeLemonsTest(HttpServletRequest request, Model model){

//        List<Content> lemons = lemonService.findByPage(new LemonQuery(1,10));

        return "lemon/home/home";
    }

    @UserLoginValidation
    @RequestMapping(value = "/lemon/lemons/add")
    public String addLemons(Model model, HttpServletRequest request){
        HeadUserInfoView userInfoView = headUserInfoManager.getUserView(request);
        model.addAttribute("headUserInfoView",userInfoView);

        return "lemon/lemons/add";
    }

    @ResponseBody
    @RequestMapping(value = "/lemon/lemons/add",method = RequestMethod.POST)
    public AjaxResponse ajaxAddLemons(HttpServletRequest request){
        //todo 登录验证



        return AjaxResponse.fail();
    }





}


