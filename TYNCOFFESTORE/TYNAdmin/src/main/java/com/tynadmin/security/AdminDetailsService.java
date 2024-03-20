package com.tynadmin.security;

import com.tynadmin.service.AdminService;
import com.tynentity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getByEmail(username);

        if(admin == null)
            throw new UsernameNotFoundException("Not found user with email: " + username);

        return new AdminDetails(admin);
    }
}
