package com.lemon.controller.lemonContents;

import com.lemon.annotation.UserLoginValidation;
import com.lemon.controller.BaseController;
import com.lemon.manager.lemon.LemonContentsManager;
import com.lemon.manager.user.HeadUserInfoManager;
import com.lemon.view.lemon.contents.PersonalCenterContentsView;
import com.lemon.view.user.HeadUserInfoView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/19.
 *
 * 个人的首页（我的发布），里面全是单个用户的内容
 */
@Controller
public class HomeWithHimselfContentsController extends BaseController{
    @Resource
    private HeadUserInfoManager headUserInfoManager;

    @Resource
    private LemonContentsManager lemonContentsManager;

    /**
     * 我的发布
     * 只看自己的发布的内容
     *
     * @param request
     * @param model
     * @return
     */
    @UserLoginValidation
    @RequestMapping(value = "/lemon/lemons/myself")
    public String myLemons(HttpServletRequest request, Model model){

        Long searchUserId = super.getUserInfoUserID(request);

        HeadUserInfoView userInfoView = headUserInfoManager.getUserView(request);
        List<PersonalCenterContentsView> lemonContentsHimself = lemonContentsManager.findLemonContentsHimself(searchUserId);

        model.addAttribute("headUserInfoView",userInfoView);
        model.addAttribute("lemonContents",lemonContentsHimself);
        model.addAttribute("isCurrentUser","true");

        return "lemon/home/myself/home";
    }

    /**
     *
     * 只看好友的发布的内容
     * 1.当查看好友的内容的时候有一个限制，不能查看到好友设为私有的内容
     * 2.不能看到好友对其设置了的特定权限的内容，比如：好友主动屏蔽了当前用户，指定其不可见
     *
     * @param friendId
     * @param request
     * @param model
     * @return
     */
    @UserLoginValidation
    @RequestMapping(value = "/lemon/lemons/{friendId:\\d+}/friend/contents")
    public String myLemons(@PathVariable  Long friendId, HttpServletRequest request, Model model) {

        Long currentUserId = super.getUserInfoUserID(request);
        Boolean currentUser = currentUserId.equals(friendId);
        if (currentUser) return "redirect:/lemon/lemons/myself";

        HeadUserInfoView userInfoView = headUserInfoManager.getUserView(request);
        List<PersonalCenterContentsView> lemonContentsHimself = lemonContentsManager.lookFriendContent(friendId,currentUserId);

        model.addAttribute("headUserInfoView",userInfoView);
        model.addAttribute("lemonContents",lemonContentsHimself);
        model.addAttribute("isCurrentUser","false");

        return "lemon/home/myself/home";
    }

}
