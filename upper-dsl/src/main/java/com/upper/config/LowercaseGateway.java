package com.upper.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway(defaultReplyChannel = "decorate")
public interface LowercaseGateway {
    @Gateway(requestChannel = "lower", replyChannel = "decorate")
    String lower(String str);
}
