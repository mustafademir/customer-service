package com.md.customerservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDto {
    private String message;
    private String token;
    private Boolean isSuccess;
}
