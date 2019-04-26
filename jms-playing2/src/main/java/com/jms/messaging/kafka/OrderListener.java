package com.jms.messaging.kafka;

import com.jms.domain.KitchenUI;
import com.jms.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(final KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
//    public void handle(final Order order) {
//        ui.displayOrder(order);
//    }
//    public void handle(final Order order, final ConsumerRecord<Order, Order> record) {
//        log.info("Received from partition {} with timestam p {}", record.partition(), record.timestamp());
//        ui.displayOrder(order);
//    }
    public void handle(final Order order, final Message<Order> message) {
        final MessageHeaders headers = message.getHeaders();
        log.info("Received from partition {} with timestamp {}", headers.get(KafkaHeaders.RECEIVED_PARTITION_ID), headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));
        //headers.get(KafkaHeaders.RECEIVED_TIMESTAMP)
        ui.displayOrder(order);
    }


}
