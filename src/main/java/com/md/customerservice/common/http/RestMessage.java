package com.md.customerservice.common.http;

import lombok.Getter;

@Getter
public class RestMessage {
    private final String message;

    public RestMessage(String message) {
        this.message = message;
    }
}
