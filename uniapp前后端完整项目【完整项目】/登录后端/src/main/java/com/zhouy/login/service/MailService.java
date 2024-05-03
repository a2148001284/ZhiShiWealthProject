package com.zhouy.login.service;

import javax.mail.MessagingException;

public interface MailService {

    void sendHtmlNotifyMail(String email, String title, String content) throws MessagingException;
}
