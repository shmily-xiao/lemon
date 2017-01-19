package com.lemon.controller.friendship;

import com.alibaba.fastjson.JSON;
import com.lemon.annotation.UserLoginValidation;
import com.lemon.controller.BaseController;
import com.lemon.manager.friends.FriendsManager;
import com.lemon.manager.user.HeadUserInfoManager;
import com.lemon.view.friends.FriendsGroupView;
import com.lemon.view.user.HeadUserInfoView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/19.
 * 好友
 */
@Controller
public class FriendShipController extends BaseController{

    @Resource
    private FriendsManager friendsManager;

    @Resource
    private HeadUserInfoManager headUserInfoManager;

    @RequestMapping(value = "/lemon/{userId:\\d+}/friends")
    @UserLoginValidation
    public String friends(@PathVariable Long userId, HttpServletRequest request, Model model){

        List<FriendsGroupView> friendGroupsViews = friendsManager.initFriendsGroup(userId);
        HeadUserInfoView userInfoView = headUserInfoManager.getUserView(request);

        model.addAttribute("headUserInfoView",userInfoView);
        model.addAttribute("friendGroupsViews", JSON.toJSONString(friendGroupsViews));

        return "/lemon/friendship/friends";
    }
}
