package com.jms.messaging;

import com.jms.domain.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
