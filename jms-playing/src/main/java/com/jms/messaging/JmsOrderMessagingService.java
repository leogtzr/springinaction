package com.jms.messaging;

import com.jms.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class JmsOrderMessagingService implements OrderMessagingService {

    private JmsTemplate jms;

    @Autowired
    public JmsOrderMessagingService(final JmsTemplate jms) {
        this.jms = jms;
    }


    @Override
    public void sendOrder(final Order order) {
        jms.send(session -> session.createObjectMessage(order));
    }
}
