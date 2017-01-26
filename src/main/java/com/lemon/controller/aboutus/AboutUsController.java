package com.lemon.controller.aboutus;

import com.lemon.annotation.UserLoginValidation;
import com.lemon.controller.BaseController;
import com.lemon.manager.user.HeadUserInfoManager;
import com.lemon.view.user.HeadUserInfoView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenGang on 2017/1/26.
 */
@Controller
public class AboutUsController extends BaseController {
    @Resource
    private HeadUserInfoManager headUserInfoManager;

    @UserLoginValidation
    @RequestMapping(value = "/lemon/aboutus", method = RequestMethod.GET)
    public String aboutUs(Model model, HttpServletRequest request) {
        HeadUserInfoView userInfoView = headUserInfoManager.getUserView(request);

        model.addAttribute("headUserInfoView",userInfoView);
        return "/lemon/aboutus/aboutus";
    }
}
