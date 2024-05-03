package com.zhouy.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouy.login.controller.UserController;
import com.zhouy.login.dao.mapper.UserMapper;
import com.zhouy.login.dao.mapper.UsualScoreRecordMapper;
import com.zhouy.login.dao.pojo.User;
import com.zhouy.login.dao.pojo.UsualScoreRecord;
import com.zhouy.login.model.Result;
import com.zhouy.login.params.MailParams;
import com.zhouy.login.service.MailService;
import com.zhouy.login.service.UserService;
import com.zhouy.login.util.CodeUtil;
import com.zhouy.login.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MailService mailService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Result register(User user) {
        //判断用户是否已经存在
        LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,user.getAccount());
        queryWrapper.last(" limit 1");
        User user1 = userMapper.selectOne(queryWrapper);
        if (user1 != null) {
            return Result.fail("您已注册", 201);
        }
        //执行注册 1.加密密码
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        //存入数据库
        userMapper.insert(user);
        return Result.success(user, "注册成功");
    }

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,user.getAccount());
        queryWrapper.last(" limit 1");
        User getUser = userMapper.selectOne(queryWrapper);
        if(getUser==null){
            return Result.fail("没有该账户",202);
        }
        if(!getUser.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))){
            return Result.fail("密码错误",203);
        }
        //有效期？
        return Result.success(getUser,"登录成功");
    }

    @Override
    public Result update(User user) {
        LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,user.getAccount());
        queryWrapper.last(" limit 1");
        User getUser = userMapper.selectOne(queryWrapper);
        if (getUser==null){
            return Result.fail("没有该账户",202);
        }
        user.setId(getUser.getId());
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        userMapper.updateById(user);
        return Result.success(user,"更新密码成功");
    }

    @Override
    public Result isLogin(HttpSession Session) {
        //从session中取出用户信息
        User sessionUser =(User)Session.getAttribute(UserController.SESSION_NAME);
        if(sessionUser==null){
            return Result.fail("用户未登录",401);
        }
        //不为空去数据库查询信息比对
        User getUser = userMapper.selectById(sessionUser.getId());
        if(getUser==null||!getUser.getPassword().equals(sessionUser.getPassword())){
            return Result.fail("用户信息错误",401);
        }
        return Result.success(getUser,"用户已经登录");
    }

    @Override
    public Result sendMail(MailParams mailParams) {
        String code = CodeUtil.randomCode();
        if(mailParams.getMail()==null){
            return Result.fail("邮箱不能为空",305);
        }
        redisTemplate.opsForValue().set(mailParams.getMail(),code,5, TimeUnit.MINUTES);
        try {
            mailService.sendHtmlNotifyMail(mailParams.getMail(),"重置密码",code);
        } catch (MessagingException e) {
            e.printStackTrace();
            return Result.fail("发送邮件失败",303);
        }
        return Result.success(code,"验证码有效期5分钟");
    }

    @Override
    public boolean checkCode(String mail,String code) {
        String getCode = (String)redisTemplate.opsForValue().get(mail);
        if(getCode==null){
            return false;
        }
        if(Objects.equals(getCode, code)){
            return true;
        }
        return false;
    }


}
