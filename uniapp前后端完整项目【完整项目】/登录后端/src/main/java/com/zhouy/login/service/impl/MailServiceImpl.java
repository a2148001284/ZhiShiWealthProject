package com.zhouy.login.service.impl;

import com.zhouy.login.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String sender;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine; // 注入模板引擎读取模板文件

    public void sendHtmlNotifyMail(String email, String title, String content) throws MessagingException {
        // 通过Context对象构建模板中变量需要的值
        Context context = new Context();
        context.setVariable("title", title);
        context.setVariable("content", content);
        // 传入变量，并渲染模板
        String mimeString = templateEngine.process("mail.html", context);
        // 创建富文本信息对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(email);
        helper.setSubject(title);
        // 给helper设置内容为我们渲染的模板内容，第二个参数为true表示内容是html格式
        helper.setText(mimeString, true);
        // 发送邮件
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron ="0 26 18 * * ?")
    public void sendMail() throws MessagingException {
        // 通过Context对象构建模板中变量需要的值
        Context context = new Context();
        LocalDateTime localDateTime =LocalDateTime.now();
        DateTimeFormatter df =DateTimeFormatter.ofPattern("yyyy:MM:dd");
        String format = df.format(localDateTime);
        context.setVariable("title",format);
        context.setVariable("content", "你不努力,谁替你坚强");
        // 传入变量，并渲染模板
        String mimeString = templateEngine.process("mail.html", context);
        // 创建富文本信息对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo("2443936488@qq.com");
        helper.setSubject(format);
        // 给helper设置内容为我们渲染的模板内容，第二个参数为true表示内容是html格式
        helper.setText(mimeString, true);
        // 发送邮件
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
