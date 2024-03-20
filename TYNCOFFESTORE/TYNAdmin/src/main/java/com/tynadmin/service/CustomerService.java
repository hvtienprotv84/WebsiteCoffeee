package com.tynadmin.service;

import com.tynadmin.exception.NotFoundException;
import com.tynadmin.repository.CustomersRepository;
import com.tynadmin.util.FileUploadUtils;
import com.tynadmin.util.RootPathImageUtils;
import com.tynentity.Category;
import com.tynentity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomersRepository customersRepository;

    public CustomerService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public Customer get(Integer id) {
        return customersRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found category with id: " + id));
    }

    public List<Customer> listAll() {
        return (List<Customer>) customersRepository.findAll();
    }

    public Customer saveOrUpdate(Customer customer) {
        return customersRepository.save(customer);
    }

    public void delete(Integer id) {
        Customer customer = get(id);
        FileUploadUtils.cleanDir(RootPathImageUtils.CATEGORY + "/" + id + "/");
        FileUploadUtils.delete(RootPathImageUtils.CATEGORY + "/" + id + "/");
        customersRepository.delete(customer);
    }
}
