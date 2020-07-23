package com.allbreak.milk.controller;

import com.alibaba.fastjson.JSON;
import com.allbreak.milk.VO.ResultVO;
import com.allbreak.milk.component.Audience;
import com.allbreak.milk.entity.Order;
import com.allbreak.milk.entity.User;
import com.allbreak.milk.service.SeckillService;
import com.allbreak.milk.utils.JwtTokenUtil;
import com.allbreak.milk.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName SeckillController
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/22 15:55
 * @Version 1.0
 */
@Slf4j
@CrossOrigin
@RestController
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private Audience audience;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/seckill/{courseNo}")
    public ResultVO<Order> seckill(@PathVariable Integer courseNo, @RequestHeader Map<String, String> headers) {
        // get userId from token in headers
        String token = headers.get("authorization");
        Integer userId = Integer.valueOf(JwtTokenUtil.getUserId(token.substring(7), audience.getBase64Secret()));
        // get user from redis
        // User user = JSON.parseObject(JSON.toJSONString(redisUtil.get(userId.toString())), User.class);
        // user.setId(userId);
        // log.info("get user from redis = {}" , user);
        return seckillService.seckillFlow(userId, courseNo);
    }
}
