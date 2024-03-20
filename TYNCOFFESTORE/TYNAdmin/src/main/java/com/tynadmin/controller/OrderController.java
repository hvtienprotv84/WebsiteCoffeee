package com.tynadmin.controller;

import com.tynadmin.service.OrderService;
import com.tynentity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public String home(@RequestParam(name="status", required = false, defaultValue = "") String status, Model model) {
        List<Order> orders = orderService.listAll();
        if(!status.isEmpty())
            orders = orders.stream().filter(m -> m.getStatus().equals(status)).collect(Collectors.toList());

        model.addAttribute("status", status);
        model.addAttribute("orders", orders);
        return "/orders/home";
    }

    @GetMapping("/details")
    public String details(Integer id, Model model) {
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        return "/orders/details";
    }

    @GetMapping("/update")
    public String updateStatus(String status, Integer id) {
        Order order = orderService.get(id);
        order.setStatus(status);
        orderService.save(order);
        return "redirect:/orders?status=" + order.getStatus();
    }
}
