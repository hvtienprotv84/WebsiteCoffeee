package com.tynadmin.controller;

import com.tynadmin.service.AdminService;
import com.tynadmin.util.FileUploadUtils;
import com.tynadmin.util.RootPathImageUtils;
import com.tynentity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("")
    public String home(Model model) {
        List<Admin> admins = adminService.listAll();
        model.addAttribute("admins", admins);
        return "/admin/home";
    }

    @GetMapping("/create")
    public String save(Model model) {
        model.addAttribute("admin", new Admin());
        return "/admin/form";
    }

    @GetMapping("/update")
    public String save(@RequestParam("id") Integer id, Model model) {
        Admin admin = adminService.get(id);
        model.addAttribute("admin", admin);
        return "/admin/form";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(Admin admin, @RequestParam("fileUpload") MultipartFile multipartFile) {
        if(!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            admin.setImage(fileName);
            admin = adminService.saveOrUpdate(admin);
            FileUploadUtils.cleanDir(RootPathImageUtils.ADMIN + "/" + admin.getId() + "/");
            FileUploadUtils.saveFile(RootPathImageUtils.ADMIN + "/" + admin.getId() + "/", fileName, multipartFile);
        } else {
            if(admin.getImage().isEmpty()) {
                admin.setImage(null);
            }
            adminService.saveOrUpdate(admin);
        }
        return "redirect:/accounts";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        adminService.delete(id);
        return "redirect:/accounts";
    }
}
