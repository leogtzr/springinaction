package com.jms.messaging;

import com.jms.domain.DummyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class DummyObjectMessagingService implements DummyMessagingService{

    private JmsTemplate jms;

    @Autowired
    public DummyObjectMessagingService(final JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void sendOrder(final DummyObject dummyObject) {
        // jms.send("localhost", session -> session.createObjectMessage(order));
        // jms.convertAndSend("localhost", order);
        jms.convertAndSend("localhost", dummyObject, this::addOrderSource);
    }

    private Message addOrderSource(final Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }

}
