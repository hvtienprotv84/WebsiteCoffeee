package com.tynuser.service;

import com.tynentity.Order;
import com.tynuser.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order get(Integer id) {
        return orderRepository.findById(id).orElseGet(() -> null);
    }

    public List<Order> listAll() {
        return (List<Order>) orderRepository.findAll();
    }

    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
