package com.demo.filter;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * Created by simpletour_java on 2015/5/27.
 */

public class SessionFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
//        System.out.println("过滤器运行了！" + userId + userName);
        if (userId == null || userName == null || userId.equals("")||userName.equals("")) {
            PrintWriter out = response.getWriter();
            String loginPage = "/login";
            StringBuilder builder = new StringBuilder();
            builder.append("<html>");
            builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
            builder.append("<script  type=\"text/javascript\">");
            builder.append("alert('you must go back to login!！！！');");
            builder.append("window.top.location.href='");
            builder.append(loginPage);
            builder.append("';");
            builder.append("</script>");
            builder.append("</html>");
            out.print(builder.toString());
        }
//        System.out.println("in preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
