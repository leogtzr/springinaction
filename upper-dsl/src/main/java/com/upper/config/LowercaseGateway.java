package com.upper.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway
public interface LowercaseGateway {
    @Gateway(requestChannel = "lower")
    String lower(String str);
}
