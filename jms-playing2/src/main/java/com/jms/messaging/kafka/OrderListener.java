package com.jms.messaging.kafka;

import com.jms.domain.KitchenUI;
import com.jms.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(final KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(final Order order) {
        ui.displayOrder(order);
    }

}
