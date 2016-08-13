package com.lemon.controller.account;

import com.lemon.domain.User;
import com.lemon.form.AjaxResponse;
import com.lemon.form.user.UserInformationForm;
import com.lemon.form.user.UserPasswordModifyForm;
import com.lemon.form.user.UserPrivacyForm;
import com.lemon.framework.mapping.excutor.MappingExcutor;
import com.lemon.query.user.UserQuery;
import com.lemon.service.IUserService;
import com.lemon.utils.Md5;
import com.lemon.view.user.UserView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Administrator on 2016/8/8 0008.
 * 个人中心，修改资料，修改密码，修改隐私权限
 */
@Controller
public class PersonalCenterController {

    @Resource
    private IUserService userService;

    /**
     *
     * 个人中心的访问
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/lemon/personal/center")
    public String personalCenter(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String account = (String)session.getAttribute("user.account");
        if (account == null || account.isEmpty()) return "redirect:/account/login";

        model.addAttribute("user",new UserView(userService.findUserByAccount(account).get()));

        return "";
    }

    /**
     *
     * 异步更新用户资料
     *
     * @param userForm
     * @param result
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lemon/personal/center/modify/information")
    public AjaxResponse updateUser(@RequestBody @Valid  UserInformationForm userForm, BindingResult result, HttpServletRequest request){

        if (result.hasErrors()) return AjaxResponse.fail().msg("保存失败").reason("数据表单没有填写完全");
        HttpSession session = request.getSession();
        String account = (String)session.getAttribute("user.account");
        if (account == null || account.isEmpty()) return AjaxResponse.fail().reason("用户没有登录").url("/account/login");

        // 理论上应该不会有错
        Optional<User> user = userService.findUserByAccount(account);
        User newUser = MappingExcutor.map(userForm);
        newUser.setId(user.get().getId());

        Optional<User> newUserOptional = userService.update(newUser);
        if (!newUserOptional.isPresent()){
            return AjaxResponse.fail().msg("更新失败").reason("网络错误");
        }

        return AjaxResponse.ok();
    }


    /**
     * 最初（2016-8-9）：更新隐私设置，类似于空间的访问限制，不排除以后会有更多的设置
     *
     * @param form
     * @param result
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lemon/personal/center/modify/privacy")
    public AjaxResponse updateUserPrivacy(@RequestBody @Valid UserPrivacyForm form, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()) return AjaxResponse.fail().msg("保存失败").reason("数据表单没有填写完全");

        HttpSession session = request.getSession();
        String account = (String)session.getAttribute("user.account");
        if (account == null || account.isEmpty()) return AjaxResponse.fail().reason("用户没有登录").url("/account/login");

        // 理论上应该不会有错
        Optional<User> user = userService.findUserByAccount(account);
        // 更新空间的隐私设置
        user.get().setZoneStatus(form.getZoneStatus());

        Optional<User> newUserOptional = userService.update(user.get());
        if (!newUserOptional.isPresent()){
            return AjaxResponse.fail().msg("更新失败").reason("网络错误");
        }

        return AjaxResponse.ok();
    }

    /**
     * 修改密码
     * @param form
     * @param result
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lemon/personal/center/modify/password")
    public AjaxResponse updatePassword( @RequestBody @Valid UserPasswordModifyForm form, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()) return AjaxResponse.fail().msg("修改失败").reason("数据表单没有填写完全");

        HttpSession session = request.getSession();
        String account = (String)session.getAttribute("user.account");
        if (account == null || account.isEmpty()) return AjaxResponse.fail().reason("用户没有登录").url("/account/login");

        // 理论上应该不会有错
        Optional<User> user = userService.findUserByAccount(account);
        if (!user.get().getPassword().equals(Md5.messageDigest(form.getOldPassword() + user.get().getSalt()))){
            return AjaxResponse.fail().msg("修改失败").reason("原密码不正确");
        }

        user.get().setPassword(Md5.messageDigest(form.getNewPassword() + user.get().getSalt()));
        Optional<User> newUser = userService.update(user.get());
        if (!newUser.isPresent()){
            return AjaxResponse.fail().msg("更新失败").reason("网络错误");
        }
        return AjaxResponse.ok();
    }


    /**
     * 这个地方主要是考虑到用户可以使用手机号登录，也可以使用QQ号码登录
     * @param account
     * @return
     */
    private Optional<User> getUser(String account){
        Optional<User> user = userService.findOne(new UserQuery(account));

        if (!user.isPresent()){
            UserQuery userQuery = new UserQuery();
            userQuery.setQqNo(account);
            user = userService.findOne(userQuery);
        }
        return user;
    }

}
