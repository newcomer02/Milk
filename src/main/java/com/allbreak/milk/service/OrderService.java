package com.allbreak.milk.service;

import com.allbreak.milk.entity.Order;

public interface OrderService {

    Order getOneOrder(Integer userId, Integer courseNo);

    Order saveOneOrder(Order order);
}
