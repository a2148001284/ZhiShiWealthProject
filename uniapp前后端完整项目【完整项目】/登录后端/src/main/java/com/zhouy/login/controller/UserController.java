package com.zhouy.login.controller;

import com.zhouy.login.dao.pojo.User;
import com.zhouy.login.model.Result;
import com.zhouy.login.params.MailParams;
import com.zhouy.login.service.MailService;
import com.zhouy.login.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(tags = "用户接口")
@RestController
@CacheConfig(cacheNames = "user")
public class UserController {

    /*
      session字段名
     */
    public static final String SESSION_NAME="userInfo";

    @Resource
    private UserService userService;

    @Resource
    private MailService mailService;

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public Result register(@RequestBody @Valid User user, BindingResult errors, HttpServletRequest request){
        if(errors.hasErrors()){
            return Result.fail(errors.getFieldError().getDefaultMessage(),300);
        }
        return userService.register(user);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody @Valid User user,BindingResult errors,HttpServletRequest request){
        if(errors.hasErrors()){
            return Result.fail(errors.getFieldError().getDefaultMessage(),300);
        }
        Result result = userService.login(user);
        //登录成绩，设置session
        if(result.getCode()==200){
            request.getSession().setAttribute(SESSION_NAME,result.getObject());
        }
        return result;
    }

    @ApiOperation("判断用户登录状态")
    @GetMapping("/islogin")
    public Result isLogin(HttpServletRequest request){
        return userService.isLogin(request.getSession());
    }


    @ApiOperation("发送邮箱验证码")
    @PostMapping("/sendMail")
    public Result sendMail(@RequestBody MailParams mailParams){
//        return Result.success(mailParams,"ok");
        return userService.sendMail(mailParams);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public Result update(@RequestHeader("mail") String mail,@RequestHeader("code") String code,@RequestBody User user , HttpServletRequest request){
        HttpSession session = request.getSession();
        User getUser =(User)session.getAttribute(SESSION_NAME);
        if(getUser==null||!getUser.getAccount().equals(user.getAccount())){
            return Result.fail("用户不一致",401);
        }
        //比对code
        boolean flag =userService.checkCode(mail,code);
        if(!flag){
            return Result.fail("验证码错误,请重新输入",304);
        }
        //比对成功
        Result update = userService.update(user);
        if(update.getCode()==200){
            session.setAttribute(SESSION_NAME,update.getObject());
        }
        return update;
    }

    @ApiOperation("用户登出")
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_NAME,null);
        return Result.success(null,"用户登出成功");
    }
}
