package com.tynentity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String birthday;

    @Column
    private String status;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {return name;}

    public  void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public  void setAddress(String address) {this.address = address;}

    public String getPhone() {return phone;}

    public  void setPhone(String phone) {this.phone = phone;}

    public String getBirthday() {return birthday;}

    public  void setBirthday(String birthday) {this.birthday = birthday;}

    public String getStatus() {return status;}

    public  void setStatus(String status) {this.status = status;}

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
