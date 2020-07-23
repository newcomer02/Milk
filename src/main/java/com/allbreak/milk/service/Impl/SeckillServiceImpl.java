package com.allbreak.milk.service.Impl;

import com.alibaba.fastjson.JSON;
import com.allbreak.milk.VO.ResultVO;
import com.allbreak.milk.entity.Course;
import com.allbreak.milk.entity.Order;
import com.allbreak.milk.entity.User;
import com.allbreak.milk.enums.ResultCode;
import com.allbreak.milk.enums.ResultEnum;
import com.allbreak.milk.service.CourseService;
import com.allbreak.milk.service.OrderService;
import com.allbreak.milk.service.SeckillService;
import com.allbreak.milk.utils.RedisUtil;
import com.allbreak.milk.utils.ResultVOUtil;
import com.allbreak.milk.utils.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SeckillServiceImpl
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/22 16:07
 * @Version 1.0
 */
@Service
@Transactional
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Map<String, Boolean> isSeckill = new HashMap<>();

    @Override
    public void cacheAllCourse() {
        List<Course> courseList = courseService.findAllCourse();

        if (courseList == null) return ;

        for (Course course : courseList) {
            redisUtil.set(course.getCourseId().toString(), course.getStock(), 60);
            redisUtil.set(course.getCourseId().toString(), course, -1);
            isSeckill.put(course.getCourseId().toString(), false);
        }
    }

    @Override
    public ResultVO<Order> seckillFlow(Integer userId, Integer courseNo) {
        // 判断库存
        if (isSeckill.get(courseNo.toString())) {
            return ResultVOUtil.error(ResultCode.FAIL.getCode(), ResultEnum.SECKILL_NO_QUOTE.getErroCode(), ResultEnum.SECKILL_NO_QUOTE.getMessage());
        }
        long result = redisUtil.decr(courseNo.toString(), 1);
        if (result <= 0) {
            isSeckill.put(courseNo.toString(), true);
            return ResultVOUtil.error(ResultCode.FAIL.getCode(), ResultEnum.SECKILL_NO_QUOTE.getErroCode(), ResultEnum.SECKILL_NO_QUOTE.getMessage());
        }

//        Course course = courseService.findOneCourse(courseNo);
//        int stock = course.getStock();
//        if (stock <= 0) {
//            return ResultVOUtil.error(ResultCode.FAIL.getCode(), ResultEnum.SECKILL_NO_QUOTE.getErroCode(), ResultEnum.SECKILL_NO_QUOTE.getMessage());
//        }
        // 判断是否已经购买
        Order order = orderService.getOneOrder(userId, courseNo);
        if (order != null) {
            return ResultVOUtil.error(ResultCode.FAIL.getCode(), ResultEnum.SECKILL_BOUGHT.getErroCode(), ResultEnum.SECKILL_BOUGHT.getMessage());
        }
        // 减库存
        // Course course = JSON.parseObject(JSON.toJSONString(redisUtil.get(courseNo.toString())), Course.class);
        //Order newOrder = seckill(userId, course);
        kafkaTemplate.send("test", userId+","+courseNo);
        return ResultVOUtil.error(ResultCode.FAIL.getCode(), ResultEnum.SECKILL_LINE_UP.getErroCode(), ResultEnum.SECKILL_LINE_UP.getMessage());
    }

    public Order seckill(Integer userId, Course course) {
        // 减库存
        int success = courseService.reduceStock(course.getCourseId());

        // 下订单
        if (success > 0) {
            Order order = new Order();
            Long snowId = SnowFlakeUtil.snowFlake.nextId();
            order.setOrderId(snowId.toString());
            order.setUserId(userId);
            order.setCourseId(course.getId());
            order.setCourseNo(course.getCourseId());
            order.setCourseName(course.getCourseName());
            order.setCreateTime(new Date());
            order.setSkPrice(course.getPrice());
            order.setPayStatus(0);
            return orderService.saveOneOrder(order);
        }
        return null;
    }

    @Override
    public Order seckillResult(Integer userId, Integer courseNo) {
        return orderService.getOneOrder(userId, courseNo);
    }
}
