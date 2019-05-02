package com.upper.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SysoutGateway {
    @Gateway(requestChannel = "sysout")
    void stdout(String str);
}
