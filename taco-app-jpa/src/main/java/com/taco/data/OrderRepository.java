package com.taco.data;

import com.taco.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
  ///Order save(Order order);
}
