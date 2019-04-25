package com.jms.messaging;

import com.jms.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import javax.jms.JMSException;
import javax.jms.Message;

public class JmsOrderMessagingService implements OrderMessagingService {

    private JmsTemplate jms;

    @Autowired
    public JmsOrderMessagingService(final JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void sendOrder(final Order order) {
        // jms.send("localhost", session -> session.createObjectMessage(order));
        // jms.convertAndSend("localhost", order);
        jms.convertAndSend("localhost", order, this::addOrderSource);
    }

    private Message addOrderSource(final Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
