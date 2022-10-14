package com.md.customerservice.api;

import com.md.customerservice.dto.AuthenticationResponseDto;
import com.md.customerservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> saveCustomer(@RequestParam("user_name") String userName,
                                      @RequestParam("password") String password) {
        return new ResponseEntity<>(authenticationService.saveCustomer(userName,password), HttpStatus.OK);
    }
}
