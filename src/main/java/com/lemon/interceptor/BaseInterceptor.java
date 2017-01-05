package com.lemon.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by simpletour_Jenkin on 2017/1/5.
 */
public abstract class BaseInterceptor  implements HandlerInterceptor{
    /**
     * 在执行方法之前
     * HandlerMethod handler = (HandlerMethod) handlerMethod;
     * handler.getMethodAnnotation(XXXX.class) // 验证是否有相应的注解在方法上面
     * Method method = handler.getMethod(); // 获取注解方法的方法
     * XXXX annotation = method.getAnnotation(XXXX.class);//获取注解方法
     * @param request
     * @param response
     * @param handler 如果注解里面有值，就从这里面获取
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }

    /**
     * 执行方法之后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 完成渲染之后的回调
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
