package com.allbreak.milk.service;

import com.allbreak.milk.VO.ResultVO;
import com.allbreak.milk.entity.Order;
import com.allbreak.milk.entity.User;

public interface SeckillService {

    ResultVO<Order> seckillFlow(Integer userId, Integer courseNo);
}
