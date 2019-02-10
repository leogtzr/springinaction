package com.taco.data;

import com.taco.domain.Order;

public interface OrderRepository {

  Order save(Order order);
  
}
