package com.jms.messaging;

import com.jms.domain.Order;

import javax.jms.JMSException;

public interface OrderReceiver {
    Order receiveOrder() throws JMSException;
}
