package com.tynadmin.controller;

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
public class ContactAdminsController {
    @Autowired
    JavaMailSender javaMailSender;
    @GetMapping("/contact_email_admin")
    public String showContactForm(){
        return "contact_email_admin";
    }

    // send mail for inline image and attachment file
    @PostMapping("/contact_email_admin")
    public String submitContactForm(HttpServletRequest request,
                                    @RequestParam("attachment") MultipartFile multipartFile) throws MessagingException, UnsupportedEncodingException {
        String fullName = request.getParameter("fullname");
        String ten = request.getParameter("fullname");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message  , true);

        //String mailSubject = fullName + " has sent a message";
        String mailSubject = "Hệ Thống TYN COFFEE STORE has sent a message";
        //String mailContent = "<p><b>Send name</b> : " + fullName + "</p>";

        //String mailContent = "<p><b>Xin Chào </b>: " + ten + "</p>";
        //String mailContent = "<p><b>Xin Chào </b>: " + ten + "<b></b></p>";
        String mailContent = "<p><b>Xin Chào</b>: " + ten + "</p>";
        mailContent += "<p><b>Tài khoản của bạn</b>: " + email + "</p>";
        //mailContent += "<p><b>Sender E-Mail </b>: " + email +"</p>";
        //mailContent += "<p><b>Tiêu Đề </b>: " + subject + "</p>";
        mailContent += "<hr><p><b>Tiêu Đề : XIN CÁM ƠN BẠN ĐÃ TẠO TÀI KHOẢN Ở TYN COFFEE STORE </b></p>";
        //mailContent += "<p><b>Nội Dung </b>: " + content + "</p>";
        mailContent += "<p><b>Nội Dung : HỆ THỐNG TYN COFFEE STORE GỬI THÔNG TIN ĐẾN BẠN!</b></p>";
        mailContent += "<p><b>Mã SecretKey : { File .txt dưới mục file đính kèm } </b></p>";
        //mailContent += "<p><b>Mã SecretKey </b>: " + secretkey + "</p>";
        mailContent += "<p><b>Lưu Ý : < XIN VUI LÒNG KHÔNG CHIA SẺ HOẶC CUNG CẤP MÃ SECRETKEY VỚI BẤT KỲ AI > </b></p>";
        mailContent += "<p><b>TYN COFFEE STORE - XIN CÁM ƠN QUÝ KHÁCH ĐÃ TIN TƯỞNG VÀ SỬ DỤNG DỊCH VỤ VÀ SẢN PHẨM!</b></p>";

        mailContent += "<hr><p><b>Hệ Thống Cửa Hàng Cà Phê - TYN COFFEE STORE</b></p>";
        mailContent += "<p><b> + Chi Nhánh 1: 249 Hoàng Văn Thụ, Phường 1, Tân Bình, Thành phố Hồ Chí Minh</b></p>";
        mailContent += "<p><b> + Chi Nhánh 2: 79 Phan Kế Bính, Đa Kao, Quận 1, Thành phố Hồ Chí Minh</b></p>";
        mailContent += "<p><b> + Chi Nhánh 3: 03 Trần Hưng Đạo, Phường Nguyễn Thái Bình, Quận 1, Thành phố Hồ Chí Minh</b></p>";
        mailContent += "<p><b> + Chi Nhánh 4: 59 Vinhome Ocean Park, Đại Dương, Đa Tốn, Gia Lâm, Hà Nội</b></p>";
        mailContent += "<hr><p><b> Website: tyncoffeestore.com.vn</b></p>";
        mailContent += "<p><b> Email: tyncoffeestore@gmail.com</b></p>";
        mailContent += "<p><b> Hotline: 093 110 3224</b></p>";
        mailContent += "<hr><p><b> Mọi Thắc Mắc Xin Vui Lòng Liên Hệ Thông Tin Trên Của TYN COFFEE STORE</b></p>";

        mailContent += "<hr><img style='width: 400px ; height: 150px' src= 'cid:logoImage'/>";
        helper.setFrom  ("vinhtien84.testemail@gmail.com" , "TYN COFFEE STORE");
        helper.setTo("tienprotv147@gmail.com"); //Đây là email được gửi đến
        helper.setSubject(mailSubject);
        helper.setText(mailContent , true);
        ClassPathResource resource = new ClassPathResource("/static/assets/img/background_email_admin.png");
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
        return "message_admin";
    }

}
