package com.allbreak.milk.service.Impl;

import com.allbreak.milk.entity.Order;
import com.allbreak.milk.repository.OrderRepository;
import com.allbreak.milk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/22 16:25
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOneOrder(Integer userId, Integer courseNo) {
        return orderRepository.findOrderByUserIdAndCourseNo(userId, courseNo);
    }

    @Override
    public Order saveOneOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }
}
