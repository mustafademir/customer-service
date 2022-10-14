package com.md.customerservice.service;

import com.md.customerservice.common.http.CustomerRoles;
import com.md.customerservice.dto.CustomerDto;
import com.md.customerservice.entity.Customer;
import com.md.customerservice.mapper.CustomerMapper;
import com.md.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_delete() {
        doNothing().when(customerRepository).deleteById(anyLong());
        customerService.delete(1L);
        verify(customerRepository,times(1)).deleteById(anyLong());
    }

    @Test
    void test_update() {
        Customer customer = generateCustomer();
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerMapper.entityToDto(any(Customer.class))).thenReturn(generateCustomerDto());
        assertEquals("md", customerService.update(generateCustomerDto(),1L).getCustomerName());
    }

    @Test
    void test_get() {
        Customer customer = generateCustomer();
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerMapper.entityToDto(any(Customer.class))).thenReturn(generateCustomerDto());
        assertEquals("md", customerService.get(1L).getCustomerName());
    }

    @Test
    void test_getAll() {
        Customer customer = generateCustomer();
        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));
        when(customerMapper.entityToDto(any(Customer.class))).thenReturn(generateCustomerDto());
        assertEquals(1, customerService.getAll().size());
    }

    private CustomerDto generateCustomerDto() {
        return CustomerDto.builder()
                .id(1L)
                .customerName("md")
                .build();
    }

    private Customer generateCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setCustomerName("md");
        customer.setRole(CustomerRoles.USER);
        customer.setPassword("password");
        return customer;
    }
}
