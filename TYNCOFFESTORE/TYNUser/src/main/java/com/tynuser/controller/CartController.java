package com.tynuser.controller;

import com.tynentity.Customer;
import com.tynentity.Order;
import com.tynentity.OrderDetail;
import com.tynentity.Product;
import com.tynuser.model.Cart;
import com.tynuser.security.CustomerDetails;
import com.tynuser.service.CustomerService;
import com.tynuser.service.OrderService;
import com.tynuser.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("carts")
public class CartController {

    private final ProductService productService;
    private final CustomerService customerService;
    private final OrderService orderService;

    public CartController(ProductService productService, CustomerService customerService, OrderService orderService) {
        this.productService = productService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    private Set<Cart> getCart(HttpSession session) {
        Set<Cart> carts = (HashSet<Cart>) session.getAttribute("carts");
        if(carts == null || carts.size() <= 0) {
            carts = new HashSet<>();
            session.setAttribute("carts", carts);
        }
        return carts;
    }

    @GetMapping("")
    public String home(HttpServletRequest request, Model model) {
        Set<Cart> carts = getCart(request.getSession());
        double total = 0;
        for (Cart item : carts) {
            total += item.getTotal();
        }
        model.addAttribute("subTotal", String.format("%,.0f", total));
        return "cart";
    }

    @GetMapping("/add")
    public String addToCart(Integer productId, int quantity, HttpServletRequest request, Principal principal) {
        Set<Cart> carts = getCart(request.getSession());

        //ĐOẠN CODE NÀY DÙNG ĐỂ CHUYỂN HƯỚNG ĐẾN TRANG ĐĂNG NHẬP CHO KHÁCH HÀNG CHƯA ĐĂNG NHẬP MÀ THÊM SẢN PHẨM VÀO GIỎ HÀNG
        //Thêm thuộc tính ( Principal principal ) ở trên
        if (principal == null) {
            // Chuyển hướng đến trang đăng nhập nếu người dùng chưa đăng nhập
            return "redirect:/login";
        }
        //ĐOẠN CODE NÀY DÙNG ĐỂ CHUYỂN HƯỚNG ĐẾN TRANG ĐĂNG NHẬP CHO KHÁCH HÀNG CHƯA ĐĂNG NHẬP MÀ THÊM SẢN PHẨM VÀO GIỎ HÀNG

        Cart cartInList = carts.stream()
                .filter(m -> m.getProduct().getId().equals(productId))
                .findFirst().orElseGet(() -> null);

        if(cartInList != null) {
            cartInList.setQuantity(cartInList.getQuantity() + quantity);
            carts.add(cartInList);
        } else {
            Product product = productService.get(productId);
            Cart cart = new Cart();
            cart.setQuantity(quantity);
            cart.setPrices(product.getPrices());
            cart.setProduct(product);
            carts.add(cart);
        }

        return "redirect:/shop";
    }

    @PostMapping("/update")
    public String update(Integer[] quantity, HttpServletRequest request) {
        Set<Cart> carts = getCart(request.getSession());
        Iterator<Cart> iterator = carts.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            Cart item = iterator.next();
            if(quantity[i] == 0) {
                i++;
                iterator.remove();
            } else {
                item.setQuantity(quantity[i++]);
            }
        }

        return "redirect:/carts";
    }

    @GetMapping("/clear")
    public String clear(HttpServletRequest request) {
        Set<Cart> carts = getCart(request.getSession());
        carts.clear();
        return "redirect:/carts";
    }

    @GetMapping("/check-out")
    public String checkOut(HttpServletRequest request, Model model) {
        Set<Cart> carts = getCart(request.getSession());
        if(carts.size() <= 0)
            return "redirect:/";
        double total = 0;
        for (Cart item : carts) {
            total += item.getTotal();
        }
        model.addAttribute("subTotal", String.format("%,.0f", total));
        return "check-out";
    }

    @PostMapping("/check-out")
    public String checkOut(@AuthenticationPrincipal CustomerDetails customerDetails, String name, String phone,
                           String address, HttpServletRequest request) {
        Set<Cart> carts = getCart(request.getSession());
        Customer customer = customerService.getByUserName(customerDetails.getUsername());
        Order order = new Order();
        order.setRecipientAddress(address);
        order.setRecipientName(name);
        order.setRecipientPhone(phone);
        order.setStatus("Chờ xử lý");
        order.setCustomer(customer);
        for(Cart item : carts) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setPrices(item.getPrices());
            orderDetail.setProduct(item.getProduct());
            List<Product> listProduct = productService.listAll();
            for (Product item1 :listProduct )
            {
                if (item1.getId() == item.getProduct().getId())
                {
                    int sub = item1.getInventory();
                    sub = sub - item.getQuantity();
                    item1.setInventory(sub);
                    productService.save(item1);
                }
            }
            order.getOrderDetails().add(orderDetail);
        }
        orderService.save(order);
        carts.clear();

        return "redirect:/carts/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirm() {
        return "confirmation";
    }

}
