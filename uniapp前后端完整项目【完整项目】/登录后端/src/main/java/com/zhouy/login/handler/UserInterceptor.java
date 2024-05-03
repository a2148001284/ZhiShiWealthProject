package com.zhouy.login.handler;

import com.zhouy.login.controller.UserController;
import com.zhouy.login.dao.pojo.User;
import com.zhouy.login.model.Result;
import com.zhouy.login.service.UserService;
import com.zhouy.login.util.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    //再controller层之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //取得session 判断用户是否登录有效，否则重定向至主页
        Result result = userService.isLogin(request.getSession());
        if(result.getCode()!=200){
            PrintWriter writer = response.getWriter();
            writer.print("尚未登录.....");
            log.info("尚未登录");
            return false;
        }
        HttpSession session = request.getSession();
        //从session中取出用户信息
        User sessionUser =(User)session.getAttribute(UserController.SESSION_NAME);
        //存入本地线程
        UserThreadLocal.put(sessionUser);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
