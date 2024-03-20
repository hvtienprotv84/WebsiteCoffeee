package com.tynadmin.service;

import com.tynadmin.exception.NotFoundException;
import com.tynadmin.repository.AdminRepository;
import com.tynadmin.util.FileUploadUtils;
import com.tynadmin.util.RootPathImageUtils;
import com.tynentity.Admin;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin saveOrUpdate(Admin admin) {
        if(admin.getId() != null) {
            Admin adminDb = get(admin.getId());

            if(admin.getPassword().trim().isEmpty()) {
                admin.setPassword(adminDb.getPassword());
            } else {
                encodePassword(admin);
            }
        } else {
            encodePassword(admin);
        }
        return adminRepository.save(admin);
    }

    public void updateStatus(Admin admin) {
        admin.setEnabled(!admin.isEnabled());
        adminRepository.save(admin);
    }

    public List<Admin> listAll() {
        return (List<Admin>) adminRepository.findAll();
    }

    public Admin get(Integer id) {
        return adminRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found admin with id: " + id));
    }

    public Admin getByEmail(String email) {
        return adminRepository.findAdminByEmail(email).orElseGet(() -> null);
    }

    public void delete(Integer id) {
        Admin admin = get(id);
        FileUploadUtils.cleanDir(RootPathImageUtils.ADMIN + "/" + id + "/");
        FileUploadUtils.delete(RootPathImageUtils.ADMIN + "/" + id + "/");
        adminRepository.delete(admin);
    }

    private void encodePassword(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
    }
}
