package com.md.customerservice.mapper;

import com.md.customerservice.dto.CustomerDto;
import com.md.customerservice.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDto entityToDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .build();
    }
}
