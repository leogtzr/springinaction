package com.jms.rest;

import com.jms.domain.DummyObject;
import com.jms.messaging.DummyMessagingService;
import com.jms.messaging.DummyObjectMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JMSController {

    private DummyObjectMessagingService dummyMessagingService;

    @Autowired
    public JMSController(final DummyObjectMessagingService dummyMessagingService) {
        this.dummyMessagingService = dummyMessagingService;
    }

    @GetMapping("/convertAndSend/order")
    public String convertAndSendDummyObject() {
        final DummyObject dummyObject = new DummyObject("Leo");
        dummyMessagingService.sendOrder(dummyObject);
        return "Convert and sent order";
    }

}
