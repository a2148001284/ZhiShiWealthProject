package com.zhouy.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhouy.login.dao.pojo.User;
import com.zhouy.login.dao.pojo.UsualScoreRecord;
import com.zhouy.login.model.Result;
import com.zhouy.login.params.MailParams;
import com.zhouy.login.vo.UserVo;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import javax.servlet.http.HttpSession;

public interface UserService{

    /**
     * 注册用户
     * @param user 用户
     * @return 注册结果
     */
    Result register(User user);

    /**
     * 登录
     * @param user 用户
     * @return 登录结果
     */
    Result login(User user);

    /**
     * 该密码，后续使用邮箱，短信
     *
     * @param user 更新的用户信息
     * @return 更新结果
     */
    Result update(User user);

    /**
     * 判断用户是否登录,即从session中取出用户id去数据库查询比对
     * @param httpSession 请求传入session
     * @return 结果
     */
    Result isLogin(HttpSession httpSession);

    /**
     * 发送验证码邮件
     * @param mailParams 目标用户
     * @return 结果
     */
    Result sendMail(MailParams mailParams);

    boolean checkCode(String mail,String code);

}
