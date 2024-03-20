package com.tynadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping("")
    public String home() {
        return "/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/thongbao_taotaikhoankhachhang_admin")
    public String thongbao_taotaikhoankhachhang_admin() {
        return "thongbao_taotaikhoankhachhang_admin";
    }

    @GetMapping("/chuyendoi_mahoa_secretkey_admin")
    public String chuyendoi_mahoa_secretkey_admin() {
        return "chuyendoi_mahoa_secretkey_admin";
    }


}
