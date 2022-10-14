package com.md.customerservice.service;

import com.md.customerservice.dto.CustomerDto;
import com.md.customerservice.entity.Customer;
import com.md.customerservice.mapper.CustomerMapper;
import com.md.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDto> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public boolean delete(Long id) {
        customerRepository.deleteById(id);
        return true;
    }

    public CustomerDto update(CustomerDto customerDto, Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setCustomerName(customerDto.getCustomerName());
            return customerMapper.entityToDto(customerRepository.save(customer));
        } else {
            throw new EntityNotFoundException("Not found for given ID: " + id);
        }
    }

    public CustomerDto get(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found for given ID: " + id));
        return customerMapper.entityToDto(customer);
    }
}

