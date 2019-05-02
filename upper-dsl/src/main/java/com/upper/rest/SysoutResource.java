package com.upper.rest;

import com.upper.config.SysoutGateway;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysoutResource {

    @Autowired
    private SysoutGateway sysoutGateway;

    @GetMapping("/print")
    public String print() {
        sysoutGateway.stdout("alv");
        return HttpSender.Response.NO_RESPONSE_BODY;
    }

}
