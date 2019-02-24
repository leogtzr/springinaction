package com.taco.data;

import com.taco.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByDeliveryZip(String deliveryZip);
    List<Order> readOdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

}
