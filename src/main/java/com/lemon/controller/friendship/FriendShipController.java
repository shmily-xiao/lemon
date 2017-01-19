package com.lemon.controller.friendship;

import com.alibaba.fastjson.JSON;
import com.lemon.annotation.UserLoginValidation;
import com.lemon.controller.BaseController;
import com.lemon.domain.impl.user.User;
import com.lemon.form.AjaxResponse;
import com.lemon.manager.friends.FriendsManager;
import com.lemon.manager.user.HeadUserInfoManager;
import com.lemon.pojo.constants.LemonConstants;
import com.lemon.service.IUserService;
import com.lemon.view.friends.FriendsGroupView;
import com.lemon.view.user.HeadUserInfoView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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

    @Resource
    private IUserService userService;

    @UserLoginValidation
    @RequestMapping(value = "/lemon/{userId:\\d+}/friends")
    public String friends(@PathVariable Long userId, HttpServletRequest request, Model model){

        List<FriendsGroupView> friendGroupsViews = friendsManager.initFriendsGroup(userId);
        HeadUserInfoView userInfoView = headUserInfoManager.getUserView(request);

        model.addAttribute("headUserInfoView",userInfoView);
        model.addAttribute("friendGroupsViews", JSON.toJSONString(friendGroupsViews));

        return "/lemon/friendship/friends";
    }

    @ResponseBody
    @RequestMapping(value = "/lemon/search/user/{account}")
    public AjaxResponse searchUser(@PathVariable String account, HttpServletRequest request){
        if (!super.isUserLoginIn(request)){
            return AjaxResponse.fail().msg("用户没有登录").url(LemonConstants.LOGIN_URL);
        }
        Optional<User> user = userService.findUserByAccount(account);
        if (!user.isPresent()) {
            return AjaxResponse.fail().msg("您查找的用户不存在！");
        }
        return AjaxResponse.ok().data(JSON.toJSONString(friendsManager.initSearchUser(user.get())));
    }


}
