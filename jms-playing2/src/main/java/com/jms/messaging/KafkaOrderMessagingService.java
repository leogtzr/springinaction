package com.jms.messaging;

import com.jms.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

    private KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    public KafkaOrderMessagingService(final KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired


    @Override
    public void sendOrder(final Order order) {
        kafkaTemplate.send("tacocloud.orders.topic", order);
        // kafkaTemplate.sendDefault(order)
    }
}
