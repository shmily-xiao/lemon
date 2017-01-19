package com.lemon.controller.lemonContents;

import com.lemon.annotation.UserLoginValidation;
import com.lemon.controller.BaseController;
import com.lemon.manager.lemon.LemonContentsManager;
import com.lemon.manager.user.HeadUserInfoManager;
import com.lemon.view.lemon.contents.PersonalCenterContentsView;
import com.lemon.view.user.HeadUserInfoView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * @param request
     * @param model
     * @return
     */
    @UserLoginValidation
    @RequestMapping(value = "/lemon/lemons/myself")
    public String myLemons(Long userId,HttpServletRequest request, Model model){

        Long searchUserId = super.getUserInfoUserID(request);
        Boolean currentUser = searchUserId.equals(userId);
        if (userId!=null && userId !=0)
            searchUserId = userId;
        HeadUserInfoView userInfoView = headUserInfoManager.getUserView(request);

        List<PersonalCenterContentsView> lemonContentsHimself = lemonContentsManager.findLemonContentsHimself(searchUserId);

        model.addAttribute("headUserInfoView",userInfoView);
        model.addAttribute("lemonContents",lemonContentsHimself);
        model.addAttribute("currentUser",currentUser);

        return "lemon/home/myself/home";
    }

}
