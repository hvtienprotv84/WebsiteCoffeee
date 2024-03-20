package com.tynuser.service;

import com.tynentity.Customer;
import com.tynuser.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer saveOrUpdate(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    public Customer get(Integer id) {
        return customerRepository.findById(id).orElseGet(() -> null);
    }

    public Customer getByUserName(String username) {
        return customerRepository.findCustomerByUsername(username).orElseGet(() -> null);
    }

    public List<Customer> listAll() {
        return (List<Customer>) customerRepository.findAll();
    }

}
