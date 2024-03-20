package com.tynuser.controller;

import com.tynentity.Customer;
import com.tynentity.Order;
import com.tynuser.security.CustomerDetails;
import com.tynuser.service.CustomerService;
import com.tynuser.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final OrderService orderService;
    private final CustomerService customerService;

    public UserController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @GetMapping("")
    public String index(@AuthenticationPrincipal CustomerDetails customerDetails,Model model) {
        Customer customer = customerService.getByUserName(customerDetails.getUsername());
        model.addAttribute("customer",customer);
        return "user";
    }

    @GetMapping("/orders")
    public String orders(@AuthenticationPrincipal CustomerDetails customerDetails, Model model) {
        Customer customer = customerService.getByUserName(customerDetails.getUsername());
        model.addAttribute("orders", customer.getOrders().stream().sorted((a, b) -> b.getId() - a.getId()).collect(Collectors.toList()));
        return "orders";
    }

    @GetMapping("/order-details")
    public String orderDetails(Integer id, Model model) {
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        return "order-details";
    }

    @GetMapping("/cancel-order")
    public String cancelOrder(Integer id) {
        Order order = orderService.get(id);
        order.setStatus("Hủy");
        orderService.save(order);
        return "redirect:/user/order-details?id=" + id;
    }

}
