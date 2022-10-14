package com.md.customerservice.service;

import com.md.customerservice.common.http.CustomerRoles;
import com.md.customerservice.dto.AuthenticationResponseDto;
import com.md.customerservice.entity.Customer;
import com.md.customerservice.repository.CustomerRepository;
import com.md.customerservice.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomerRepository customerRepository;

    public AuthenticationResponseDto saveCustomer(String userName, String password) {
        Customer customer = new Customer();
        customer.setCustomerName(userName);
        customer.setPassword(new BCryptPasswordEncoder().encode(password));
        customer.setRole(CustomerRoles.USER);
        UserDetails userDetails = userDetailsService.createUserDetails(userName, customer.getPassword());
        customerRepository.save(customer);
        return AuthenticationResponseDto.builder()
                .message("Customer created successfully")
                .token(jwtTokenUtil.generateToken(userDetails))
                .isSuccess(true)
                .build();
    }
}
