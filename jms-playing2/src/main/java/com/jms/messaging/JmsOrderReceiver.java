package com.jms.messaging;

import com.jms.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jms;

    @Autowired
    public JmsOrderReceiver(final JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public Order receiveOrder() throws JMSException {
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }
}
