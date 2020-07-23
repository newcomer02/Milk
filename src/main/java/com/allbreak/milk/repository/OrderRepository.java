package com.allbreak.milk.repository;

import com.allbreak.milk.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName OrderRepository
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/22 16:28
 * @Version 1.0
 */
@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer> {

    Order findOrderByUserIdAndCourseNo(Integer userId, Integer courseNo);
}
