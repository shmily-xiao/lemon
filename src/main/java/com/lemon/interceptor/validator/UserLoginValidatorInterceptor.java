package com.lemon.interceptor.validator;

import com.lemon.annotation.UserLoginValidation;
import com.lemon.interceptor.BaseInterceptor;
import com.lemon.pojo.constants.LemonConstants;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by simpletour_Jenkin on 2017/1/5.
 */
public class UserLoginValidatorInterceptor extends BaseInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 如果没有注解直接放过
        if (handlerMethod.getMethodAnnotation(UserLoginValidation.class)==null){
            return true;
        }
        Long userId = (Long) session.getAttribute(LemonConstants.USER_SEESSION_ID);
        String account = (String) session.getAttribute(LemonConstants.USER_SEESSION_ACCOUNT);
        if (userId != null && account != null && !account.isEmpty()){
            return true;
        }
//        // 当前访问的地址
//        String path = String.format("%s?%s", request.getRequestURI(), request.getQueryString());
//        if (!(path == null || path.isEmpty()))
//            request.getSession().setAttribute(String.format("%s.%s", this.getClass().getName(), "redirectUrl"), path);
        // 去登陆
        response.getWriter().write(
                "<html>" +
                        "   <head>" +
                        "       <script type='text/javascript'>top.location.href='" +
                        "/lemon/account/login" +
                        "';</script>" +
                        "   </head>" +
                        "   <body></body>" +
                        "</html>"
        );
        return false;
    }
}
