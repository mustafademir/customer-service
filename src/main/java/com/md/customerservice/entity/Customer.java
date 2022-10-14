package com.md.customerservice.entity;

import com.md.customerservice.common.http.CustomerRoles;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customerName",unique=true)
    private String customerName;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private CustomerRoles role;
}
