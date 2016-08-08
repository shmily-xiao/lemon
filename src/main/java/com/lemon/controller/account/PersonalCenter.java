package com.lemon.controller.account;

import com.lemon.domain.User;
import com.lemon.query.user.UserQuery;
import com.lemon.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
@Controller
public class PersonalCenter {

    @Resource
    private IUserService userService;


    @RequestMapping(value = "/personal/center")
    public String personalCenter(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String account = (String)session.getAttribute("user.account");
        Optional<User> user = userService.findOne(new UserQuery(account));

        return "";
    }
}
