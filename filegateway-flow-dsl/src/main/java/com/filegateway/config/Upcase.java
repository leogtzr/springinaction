package com.filegateway.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface Upcase {
    @Gateway(requestChannel = "upper")
    String up(String str);
}
