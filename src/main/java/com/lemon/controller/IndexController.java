package com.lemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by simpletour_Jenkin on 2016/8/10.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "/lemon/account/index";
    }
}
