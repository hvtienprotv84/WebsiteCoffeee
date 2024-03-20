package com.tynadmin.controller;

import com.tynadmin.repository.User2Repository;
import com.tynentity.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecretKeyController {
    @Autowired
    private User2Repository userRepository;


    @GetMapping("/secretkey")
    public String showAccountForm(Model model) {
        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("user", new User2());
        return "quanly_secretkey";
    }

    @PostMapping("/save")
    public String saveAccount(@ModelAttribute User2 user) {
        userRepository.save(user);
        return "redirect:/secretkey";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("currentPage", "edit");
        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        return "quanly_secretkey";
    }

    @PostMapping("/update")
    public String updateAccount(@ModelAttribute User2 user) {
        userRepository.save(user);
        return "redirect:/secretkey";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/secretkey";
    }
}