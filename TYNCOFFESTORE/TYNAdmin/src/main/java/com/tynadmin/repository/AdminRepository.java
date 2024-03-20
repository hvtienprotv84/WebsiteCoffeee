package com.tynadmin.repository;

import com.tynentity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

    @Query("SELECT a FROM Admin a WHERE a.email LIKE ?1")
    public Optional<Admin> findAdminByEmail(String email);
}
