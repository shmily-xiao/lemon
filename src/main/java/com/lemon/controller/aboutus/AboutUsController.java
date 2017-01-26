package com.lemon.controller.aboutus;

import com.lemon.annotation.UserLoginValidation;
import com.lemon.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chenGang on 2017/1/26.
 */
@Controller
public class AboutUsController extends BaseController {

    @UserLoginValidation
    @RequestMapping(value = "/lemon/aboutus", method = RequestMethod.GET)
    public String aboutUs() {
        return "/lemon/aboutus/aboutus";
    }
}
