package com.tynuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@Controller
public class ContactController {
    @Autowired
    JavaMailSender javaMailSender;
    @GetMapping("/contact")
    public String showContactForm(){
        return "contact_email";
    }

    @PostMapping("/contact")
    public String submitContactForm(HttpServletRequest request,
                                    @RequestParam("attachment") MultipartFile multipartFile) throws MessagingException, UnsupportedEncodingException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message  , true);

        String mailSubject = fullName + " has sent a message";
        String mailContent = "<p><b>Họ và Tên Người Gửi </b> : " + fullName + "</p>";
        mailContent += "<p><b>E-Mail Người Gửi </b>: " + email +"</p>";
        mailContent += "<p><b>Tiêu Đề </b>: " + subject + "</p>";
        mailContent += "<p><b>Nội Dung </b>: " + content + "</p>";
        mailContent += "<hr><img style='width: 400px ; height: 150px' src= 'cid:logoImage'/>";
        helper.setFrom  ("funnytvvn@gmail.com" , "TYN COFFEE STORE");
        helper.setTo("vinhtien84.testemail@gmail.com"); //Đây là email thứ 2 được gửi Email
        helper.setSubject(mailSubject);
        helper.setText(mailContent , true);
        ClassPathResource resource = new ClassPathResource("/static/img/background_email.png");
        helper.addInline("logoImage" , resource);
        if (!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            InputStreamSource source = new InputStreamSource() {
                @Override
                public InputStream getInputStream() throws IOException {
                    return multipartFile.getInputStream();
                }
            };
            helper.addAttachment(fileName , source);
        }
        javaMailSender.send(message);
        return "message";
    }

}
