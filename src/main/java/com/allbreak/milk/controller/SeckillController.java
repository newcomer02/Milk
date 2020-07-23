package com.allbreak.milk.controller;

import com.alibaba.fastjson.JSON;
import com.allbreak.milk.VO.ResultVO;
import com.allbreak.milk.component.Audience;
import com.allbreak.milk.entity.Order;
import com.allbreak.milk.entity.User;
import com.allbreak.milk.enums.ResultCode;
import com.allbreak.milk.enums.ResultEnum;
import com.allbreak.milk.service.SeckillService;
import com.allbreak.milk.utils.JwtTokenUtil;
import com.allbreak.milk.utils.RedisUtil;
import com.allbreak.milk.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
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
public class SeckillController implements InitializingBean {

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

    @GetMapping("/seckillResulr/{courseNo}")
    public ResultVO<Order> seckillResult(@PathVariable Integer courseNo, @RequestHeader Map<String, String> headers) {
        String token = headers.get("authorization");
        Integer userId = Integer.valueOf(JwtTokenUtil.getUserId(token.substring(7), audience.getBase64Secret()));
        Order order = seckillService.seckillResult(userId, courseNo);
        if (order == null) {
            return ResultVOUtil.error(ResultCode.FAIL.getCode(), ResultEnum.SECKILL_LINE_UP.getErroCode(), ResultEnum.SECKILL_LINE_UP.getMessage());
        }
        return ResultVOUtil.success(order);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        seckillService.cacheAllCourse();
    }
}
