package com.lemon.controller.contentPlans;

import com.lemon.form.AjaxResponse;
import com.lemon.manager.lemon.LemonContentsManager;
import com.lemon.pojo.constants.LemonConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wangzaijun on 2017/7/4.
 */
@Controller
public class ContentPlanController {

    @Resource
    private LemonContentsManager lemonContentsManager;

    @ResponseBody
    @RequestMapping(value = "/lemon/finish/{contentId:\\d+}", method = RequestMethod.POST)
    public AjaxResponse finishMyPlan(@PathVariable Long contentId, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(LemonConstants.USER_SEESSION_ID);
        Boolean finished = lemonContentsManager.finishedTheContentPlan(contentId, userId);
        return finished?AjaxResponse.ok():AjaxResponse.fail();
    }
}
