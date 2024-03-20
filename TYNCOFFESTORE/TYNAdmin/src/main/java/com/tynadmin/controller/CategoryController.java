package com.tynadmin.controller;

import com.tynadmin.repository.CustomersRepository;
import com.tynadmin.service.CategoryService;
import com.tynadmin.service.CustomerService;
import com.tynadmin.util.FileUploadUtils;
import com.tynadmin.util.RootPathImageUtils;
import com.tynentity.Category;
import com.tynentity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String home(Model model) {
        List<Category> categories = categoryService.listAll();
        model.addAttribute("categories", categories);
        return "/category/home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "/category/form";
    }

    @GetMapping("/update")
    public String update(@RequestParam Integer id, Model model) {
        Category category = categoryService.get(id);
        model.addAttribute("category", category);
        return "/category/form";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(Category category,
                               @RequestParam(name="fileUpload") MultipartFile multipartFile) {

        if(!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            category.setImage(fileName);
            category = categoryService.saveOrUpdate(category);
            FileUploadUtils.cleanDir(RootPathImageUtils.CATEGORY + "/" + category.getId() + "/");
            FileUploadUtils.saveFile(RootPathImageUtils.CATEGORY + "/" + category.getId() + "/", fileName, multipartFile);
        } else {
            categoryService.saveOrUpdate(category);
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete")
    public String delete(Integer id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }

    @GetMapping("/unactive")
    public String unActive(Integer id){
        Category categories  = categoryService.get(id);
        categories.setStatus("không hoạt động");
        categoryService.saveOrUpdate(categories);
        return "redirect:/categories";
    }

    @GetMapping("/active")
    public String active(Integer id){
        Category categories  = categoryService.get(id);
        categories.setStatus("hoạt động");
        categoryService.saveOrUpdate(categories);
        return "redirect:/categories";
    }
}
