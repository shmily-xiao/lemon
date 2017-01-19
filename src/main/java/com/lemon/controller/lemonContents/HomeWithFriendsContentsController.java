package com.lemon.controller.lemonContents;

import com.alibaba.fastjson.JSON;
import com.lemon.annotation.UserLoginValidation;
import com.lemon.controller.BaseController;
import com.lemon.form.AjaxResponse;
import com.lemon.form.lemonContents.LemonContentsAddForm;
import com.lemon.manager.lemon.LemonContentsManager;
import com.lemon.manager.user.HeadUserInfoManager;
import com.lemon.pojo.constants.LemonConstants;
import com.lemon.view.lemon.add.LemonAddView;
import com.lemon.view.lemon.contents.LemonContentsElementView;
import com.lemon.view.user.HeadUserInfoView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by simpletour_Jenkin on 2016/8/10.
 * 首页(朋友圈)
 */
@Controller
public class HomeWithFriendsContentsController extends BaseController{

    @Resource
    private HeadUserInfoManager headUserInfoManager;

    @Resource
    private LemonContentsManager lemonContentsManager;

    /**
     * 首页
     * 所有人都可以看的，类似于微博
     * @param request
     * @param model
     * @return
     */
//    @RequestMapping(value = "/lemon/lemons/all")
    public String lemonsHome(HttpServletRequest request, Model model){



        return "lemon/home/all/home";
    }

    /**
     *
     * 首页
     * 只能看到自己的和好友的内容，类似于微信的朋友圈
     *
     * @param request
     * @param model
     * @return
     */
    @UserLoginValidation
    @RequestMapping(value = "/lemon/lemons/friends")
    public String homeLemonsFriend(HttpServletRequest request, Model model){

        Long userId = super.getUserInfoUserID(request);
        List<LemonContentsElementView> lemonContentsWithFriend = lemonContentsManager.findLemonContentsWithFriend(userId);
        HeadUserInfoView userInfoView = headUserInfoManager.getUserView(request);

        model.addAttribute("headUserInfoView",userInfoView);
        model.addAttribute("lemonContents", JSON.toJSONString(lemonContentsWithFriend));
        return "lemon/home/friends/home";
    }

    /**
     * 进入添加内容页面
     * @param model
     * @param request
     * @return
     */
    @UserLoginValidation
    @RequestMapping(value = "/lemon/lemons/add")
    public String addLemons(Model model, HttpServletRequest request){
        HeadUserInfoView userInfoView = headUserInfoManager.getUserView(request);
        LemonAddView lemonAddView = lemonContentsManager.initAddView();
        model.addAttribute("headUserInfoView",userInfoView);
        model.addAttribute("lemonAddView",lemonAddView);

        return "lemon/lemons/add";
    }

    /**
     *
     * 异步添加内容
     * @param form
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lemon/lemons/add",method = RequestMethod.POST)
    public AjaxResponse ajaxAddLemons(@RequestBody LemonContentsAddForm form, HttpServletRequest request){
        if (!super.isUserLoginIn(request)){
            return AjaxResponse.fail().msg("用户没有登录").url(LemonConstants.LOGIN_URL);
        }
        Boolean addSuccess = lemonContentsManager.addContent(form,super.getUserInfoUserID(request));

        return addSuccess?AjaxResponse.ok().url("/lemon/lemons/friends"):AjaxResponse.fail();
    }



}


