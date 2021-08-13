package com.ourchat.common.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
@Component
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.netty}")
    private String port;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        System.out.println("from:"+from);
        System.out.println("port"+port);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public void sendSimpleMail(String to, String subject, String content, String... cc) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {

    }

    @Override
    public void sendHtmlMail(String to, String subject, String content, String... cc) {

    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException {

    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath, String... cc) {

    }

    @Override
    public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException {

    }

    @Override
    public void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc) {

    }
}
