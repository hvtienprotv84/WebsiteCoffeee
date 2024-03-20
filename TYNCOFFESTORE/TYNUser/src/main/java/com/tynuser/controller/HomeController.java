package com.tynuser.controller;

import com.tynentity.Customer;
import com.tynentity.Product;
import com.tynuser.service.CategoryService;
import com.tynuser.service.CustomerService;
import com.tynuser.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ProductService productService;
    private final CustomerService customerService;
    private final CategoryService categoryService;

    public HomeController(ProductService productService, CustomerService customerService, CategoryService categoryService) {
        this.productService = productService;
        this.customerService = customerService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String home(Model model) {
        List<Product> products = productService.listAll().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("products", products);
        return "index";
    }


    @GetMapping("details")
    public String details(Integer id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "details";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String register(Customer customer, RedirectAttributes redirectAttributes) {
        Customer customerDb = customerService.getByUserName(customer.getUsername());
        if(customerDb != null) {
            redirectAttributes.addFlashAttribute("message", "Tài khoản đã tồn tại");
            return "redirect:/register";
        }

        customerService.saveOrUpdate(customer);

        return "redirect:/login";
    }

    @GetMapping("/shop")
    public String shop(@RequestParam(required = false, defaultValue = "0") Integer id, Model model) {
        List<Product> products = productService.listAll();

        if(id > 0) {
            products = products.stream().filter(m -> m.getCategory().getId().equals(id)).collect(Collectors.toList());
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.listAll());
        return "shop";
    }

    @GetMapping("/recruit")
    public  String recruit(){
        return "recruit";
    }

    @GetMapping("/info")
    public  String info(){
        return "info";
    }

    @GetMapping("/findResult")
    public String findResult(){
        return "findResult";
    }

    @GetMapping("/search")
    public String search(Model model)
    {
        List<Product> products = productService.listAll();
        model.addAttribute("products", products);
        return "search";
    }
    @GetMapping("/ListTinTuc")
    public  String ListTinTuc(){
        return "ListTinTuc";
    }
    @GetMapping("/ListAmNhac")
    public  String ListAmNhac(){
        return "ListAmNhac";
    }
    @GetMapping("/ListGame")
    public  String ListGame(){
        return "ListGame";
    }

    @GetMapping("/Game_TicTacToe")
    public  String Game_TicTacToe(){
        return "Game_TicTacToe";
    }

    @GetMapping("/AmNhac_1")
    public  String AmNhac_1(){
        return "AmNhac_1";
    }

    @GetMapping("/maytinh_popup")
    public  String maytinh_popup(){
        return "maytinh_popup";
    }

    @GetMapping("/AmNhac_2")
    public  String AmNhac_2(){
        return "AmNhac_2";
    }

    @GetMapping("/Game-RPS")
    public  String Game_RPS(){
        return "Game-RPS";
    }

    @GetMapping("/Game_Keo_Bua_Bao_PvsComputer")
    public  String Game_Keo_Bua_Bao_PvsComputer(){
        return "Game_Keo_Bua_Bao_PvsComputer";
    }

    // Danh Sách Tin Tức Ngày 27.09.2023
    @GetMapping("/chu-tich-nuoc-gui-thu-chuc-tet-trung-thu-cho-thieu-nien-nhi-dong-27092023")
    public  String TinTuc270923(){
        return "chu-tich-nuoc-gui-thu-chuc-tet-trung-thu-cho-thieu-nien-nhi-dong-27092023";
    }

    @GetMapping("/reuters-viet-nam-se-khoi-dong-lai-mo-dat-hiem-vao-nam-toi-270923")
    public  String TinTuc270923_1() {
        return "reuters-viet-nam-se-khoi-dong-lai-mo-dat-hiem-vao-nam-toi-270923";
    }

    @GetMapping("/cuu-tong-thong-trump-co-duoc-mua-sung-270923")
    public  String TinTuc270923_2() {
        return "cuu-tong-thong-trump-co-duoc-mua-sung-270923";
    }

    @GetMapping("/can-canh-smartphone-xiaomi-13t-pro-trang-bi-ong-kinh-leica-270923")
    public  String TinTuc270923_3() {
        return "can-canh-smartphone-xiaomi-13t-pro-trang-bi-ong-kinh-leica-270923";
    }

    @GetMapping("/4-mau-o-to-pho-thong-gia-500-700-trieu-phu-hop-voi-phai-dep-270923")
    public  String TinTuc270923_4() {
        return "4-mau-o-to-pho-thong-gia-500-700-trieu-phu-hop-voi-phai-dep-270923";
    }

    // Danh Sách Tin Tức Ngày 27.09.2023
    @GetMapping("/diachichinhanhggmap")
    public  String diachichinhanhggmap() {
        return "diachichinhanhggmap";
    }

    @GetMapping("/menu-sanpham")
    public  String menusanpham() {
        return "menu-sanpham";
    }

    @GetMapping("/timkiemsanpham")
    public  String timkiemsanpham() {
        return "timkiemsanpham";
    }

    @GetMapping("/ListTinTucCoffee")
    public  String ListTinTucCoffee(){
        return "ListTinTucCoffee";
    }

    @GetMapping("/ListTinTucCoffee2")
    public  String ListTinTucCoffee2(){
        return "ListTinTucCoffee2";
    }

    @GetMapping("/ListTinTucCoffee3")
    public  String ListTinTucCoffee3(){
        return "ListTinTucCoffee3";
    }
    @GetMapping("/tintuc_trungthudoanvien")
    public  String tintuc_trungthudoanvien(){
        return "tintuc_trungthudoanvien";
    }

    @GetMapping("/voucher_gift")
    public  String voucher_gift(){
        return "voucher_gift";
    }

    @GetMapping("/ListThongTinUuDaiCoffee")
    public  String ListThongTinUuDaiCoffee(){
        return "ListThongTinUuDaiCoffee";
    }

    @GetMapping("/thongtinuudai_thang11_2023")
    public  String thongtinuudai_thang11_2023(){
        return "thongtinuudai_thang11_2023";
    }

    @GetMapping("/ListThongTinUuDaiCoffee2")
    public  String ListThongTinUuDaiCoffee2(){
        return "ListThongTinUuDaiCoffee2";
    }

    @GetMapping("/ListCachPhaCoffee")
    public  String ListCachPhaCoffee(){
        return "ListCachPhaCoffee";
    }

    @GetMapping("/cachphacoffee_phinnho")
    public  String cachphacoffee_phinnho(){
        return "cachphacoffee_phinnho";
    }

    @GetMapping("/cachphacoffee_phinlon")
    public  String cachphacoffee_phinlon(){
        return "cachphacoffee_phinlon";
    }

    @GetMapping("/CamHungKhoiNghiep_TYNCOFFEE")
    public  String CamHungKhoiNghiep_TYNCOFFEE(){
        return "CamHungKhoiNghiep_TYNCOFFEE";
    }

    @GetMapping("/huongdanthanhtoan")
    public  String huongdanthanhtoan(){
        return "huongdanthanhtoan";
    }

    @GetMapping("/index_groupchat")
    public  String index_groupchat(){
        return "index_groupchat";
    }
}
