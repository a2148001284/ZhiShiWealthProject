package com.zhouy.login.controller;

import com.zhouy.login.dao.pojo.User;
import com.zhouy.login.model.Result;
import com.zhouy.login.params.MailParams;
import com.zhouy.login.util.UserThreadLocal;
import com.zhouy.login.util.excel.MyExcelUtil;
import com.zhouy.login.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "测试")

@RestController
public class TestController {

    @Resource
    private MyExcelUtil myExcelUtil;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @PostMapping("/test")
    public Result test(@RequestBody UserVo userVo){
        return Result.success(userVo,"ok");
    }

    @ApiOperation("登录后测试threadLocal")
    @GetMapping("/threadlocal")
    public Result test2(){
        User user = UserThreadLocal.get();
        return Result.success(user,"success");
    }

    @GetMapping("/null")
    public void test3(){
        throw new NullPointerException("可能空指针");
    }

    @GetMapping("/index")
    public void test4(){
        myExcelUtil.throwIndexOutOfBoundsException();
    }

    @GetMapping("/testRedis")
    public void testRedis(){
        redisTemplate.opsForValue().set("zhouy","nb");
    }
}
