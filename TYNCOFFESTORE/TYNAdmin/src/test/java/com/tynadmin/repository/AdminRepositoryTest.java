package com.tynadmin.repository;

import com.tynadmin.service.AdminService;
import com.tynentity.Admin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void testSaveAdmin() {
        Admin admin = new Admin();
        admin.setEmail("tienadmin123@gmail.com");
        admin.setPassword("$2a$12$3J68sDs5578217lUY1f3g.7TwDkNmDGXncVA63IVYvDSsdJPCdH5K");
        //mật khẩu = tienadmin456789
        admin.setFirstName("Huỳnh");
        admin.setLastName("Vĩnh Tiến");
        admin.setEnabled(true);

        admin = adminRepository.save(admin);
        Assertions.assertTrue(admin.getId() > 0);
    }

}
