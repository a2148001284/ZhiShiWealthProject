package com.zhouy.login;

import com.zhouy.login.controller.UploadController;
import com.zhouy.login.dao.pojo.Class;
import com.zhouy.login.model.Result;
import com.zhouy.login.service.ClassService;
import com.zhouy.login.service.MailService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@SpringBootTest
class LoginApplicationTests {

    @Autowired
    private MailService mailService;

    @Autowired
    private UploadController uploadController;

    @Resource
    private ClassService classService;

    @Test
    void test() {
        try {
            mailService.sendHtmlNotifyMail("2443936488@qq.com","test","test");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Test
    void test2(){
        Class ta =new Class();
        ta.setTeacherId(1);
        ta.setClassName("adf");
        ta.setName("ylm");
        Result result = classService.addClass(ta);
        System.out.println(result);
    }
//    @Test
//    void test2(){
//        uploadController.getFile();
//    }
}
