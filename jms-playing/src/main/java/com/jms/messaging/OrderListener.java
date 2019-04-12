package com.jms.messaging;

import com.jms.domain.KitchenUI;
import com.jms.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(final KitchenUI ui) {
        this.ui = ui;
    }

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveORder(final Order order) {
        ui.displayOrder(order);
    }
}
