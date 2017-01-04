package com.lemon.controller;

import com.lemon.pojo.constants.LemonConstants;
import com.lemon.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by simpletour_Jenkin on 2017/1/3.
 */

public class BaseController {

    /**
     * 判断用户是否登录了
     *
     * @param request
     * @return 登陆了返回true
     */
    protected Boolean isUserLoginIn(HttpServletRequest request){
        String mobile = (String) request.getSession().getAttribute(LemonConstants.USER_SEESSION_MOBILE);
        Long userId = (Long) request.getSession().getAttribute(LemonConstants.USER_SEESSION_ID);
        return StringUtils.notEmpty(mobile) && userId!=null;
    }

    /**
     * 得到用户的手机号码
     * @param request
     * @return
     */
    protected String getUserInfoMobile(HttpServletRequest request){
        if (isUserLoginIn(request)){
            return (String)request.getSession().getAttribute(LemonConstants.USER_SEESSION_MOBILE);
        }
        return "";
    }

    /**
     * 得到用户的用户ID
     * @param request
     * @return
     */
    protected Long getUserInfoUserID(HttpServletRequest request){
        if (isUserLoginIn(request)){
            return (Long)request.getSession().getAttribute(LemonConstants.USER_SEESSION_ID);
        }
        return null;
    }

}
