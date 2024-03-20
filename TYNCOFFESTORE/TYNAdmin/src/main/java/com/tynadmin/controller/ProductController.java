package com.tynadmin.controller;

import com.tynadmin.service.CategoryService;
import com.tynadmin.service.ProductService;
import com.tynadmin.util.FileUploadUtils;
import com.tynadmin.util.RootPathImageUtils;
import com.tynentity.Product;
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
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String home(Model model) {
        List<Product> products = productService.listAll();
        model.addAttribute("products", products);
        return "/product/home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.listAll());
        return "/product/form";
    }

    @GetMapping("/update")
    public String update(@RequestParam Integer id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.listAll());
        return "/product/form";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(Product product, @RequestParam("fileUpload") MultipartFile multipartFile) {
        if(!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setImage(fileName);
            product = productService.save(product);
            FileUploadUtils.cleanDir(RootPathImageUtils.PRODUCT + "/" + product.getId() + "/");
            FileUploadUtils.saveFile(RootPathImageUtils.PRODUCT + "/" + product.getId() + "/", fileName, multipartFile);
        } else {
            productService.save(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String delete(Integer id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
