package com.lemon.controller.account;

import com.lemon.domain.User;
import com.lemon.form.AjaxResponse;
import com.lemon.form.user.UserInformationForm;
import com.lemon.query.user.UserQuery;
import com.lemon.service.IUserService;
import com.lemon.utils.ConvertUtils;
import com.lemon.view.user.UserView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping(value = "/personal/center")
    public String personalCenter(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String account = (String)session.getAttribute("user.account");
        if (account == null || account.isEmpty()) return "redirect:/account/login";

        model.addAttribute("user",new UserView(this.getUser(account).get()));

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
    @RequestMapping(value = "/personal/center/modify")
    public AjaxResponse updateUser(@RequestBody @Valid  UserInformationForm userForm, BindingResult result, HttpServletRequest request){

        if (result.hasErrors()) return AjaxResponse.fail().msg("保存失败").reason("数据表单没有填写完全");
        HttpSession session = request.getSession();
        String account = (String)session.getAttribute("user.account");
        if (account == null || account.isEmpty()) return AjaxResponse.fail().reason("用户没有登录").url("/account/login");

        Optional<User> user = this.getUser(account);
        User newUser = ConvertUtils.convert(userForm,User.class);
        newUser.setId(user.get().getId());


        return AjaxResponse.fail();


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
