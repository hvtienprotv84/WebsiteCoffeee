package com.tynuser.security;

import com.tynentity.Customer;
import com.tynuser.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.getByUserName(username);
        if(customer == null) {
            throw new UsernameNotFoundException("Not found customer with username: " + username);
        }
        return new CustomerDetails(customer);
    }
}
