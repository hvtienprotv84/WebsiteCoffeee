package com.tynuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class Contact2Controller {
    @Autowired
    JavaMailSender javaMailSender;
    @GetMapping("/contact2")
    public String showContactForm(){
        return "contact_email2";
    }

    @PostMapping("/contact2")
    public String submitContactForm(HttpServletRequest request)
            throws MessagingException, UnsupportedEncodingException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message  , true);

        String mailSubject = fullName + " has sent a message - Đăng Ký Nhận Thông Tin Mới";
        String mailContent = "<p><b>Họ và Tên Người Gửi</b> : " + fullName + "</p>";
        mailContent += "<p><b>E-Mail Người Gửi</b>: " + email +"</p>";
        mailContent += "<hr><img style='width: 400px ; height: 150px' src= 'cid:logoImage'/>";
        helper.setFrom  ("funnytvvn@gmail.com" , "TYN COFFEE STORE 2");
        helper.setTo("vinhtien84.testemail@gmail.com"); //Đây là email thứ 2 được gửi Email
        helper.setSubject(mailSubject);
        helper.setText(mailContent , true);
        ClassPathResource resource = new ClassPathResource("/static/img/background_email.png");
        helper.addInline("logoImage" , resource);

        javaMailSender.send(message);
        return "message2";
    }

}
