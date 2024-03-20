package com.tynadmin.controller;

import com.tynadmin.service.CategoryService;
import com.tynadmin.service.CustomerService;
import com.tynadmin.util.FileUploadUtils;
import com.tynadmin.util.RootPathImageUtils;
import com.tynentity.Category;
import com.tynentity.Customer;
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
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public String home(Model model) {
        List<Customer> customers = customerService.listAll();
        model.addAttribute("customers", customers);
        return "/customer/home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "/category/form";
    }

}
