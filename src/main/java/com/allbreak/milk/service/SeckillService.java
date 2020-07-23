package com.allbreak.milk.service;

import com.allbreak.milk.VO.ResultVO;
import com.allbreak.milk.entity.Course;
import com.allbreak.milk.entity.Order;

public interface SeckillService {

    ResultVO<Order> seckillFlow(Integer userId, Integer courseNo);

    void cacheAllCourse();

    Order seckill(Integer userId, Course course);

    Order seckillResult(Integer userId, Integer courseNo);
}
