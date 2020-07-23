package com.allbreak.milk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName helloworldController
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/13 23:56
 * @Version 1.0
 */
// 此注解相当于@Controller + @ResponseBody
@RestController
public class helloworldController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "world")String name) {
        return "hello " + name;
    }
}
