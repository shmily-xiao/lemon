package com.lemon.controller.lemon;

import com.lemon.controller.BaseController;
import com.lemon.domain.impl.content.Content;
import com.lemon.query.lemon.LemonQuery;
import com.lemon.service.IContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by simpletour_Jenkin on 2016/8/10.
 * 首页，个人中心
 */
@Controller
public class LemonController extends BaseController{

    @Resource
    private IContentService lemonService;

    /**
     * 首页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/lemon/lemons")
    public String lemonsHome(HttpServletRequest request, Model model){

        List<Content> lemons = lemonService.findByPage(new LemonQuery(1,10));

        return "lemon/home/home";
    }

    /**
     * 首页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/lemon/lemons/test")
    public String homeLemonsTest(HttpServletRequest request, Model model){

//        List<Content> lemons = lemonService.findByPage(new LemonQuery(1,10));

        return "lemon/home/home";
    }

    @RequestMapping(value = "/lemon/lemons/add")
    public String addLemons(){

        return "lemon/lemons/add";
    }



}


