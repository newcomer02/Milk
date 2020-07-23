package com.allbreak.milk;

import com.allbreak.milk.component.Audience;
import com.allbreak.milk.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName JwtTest
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/22 21:27
 * @Version 1.0
 */
@SpringBootTest
public class JwtTest {

    @Autowired
    private Audience audience;

    @Test
    public void getUserIdFromJwtUtil() {
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoidXNlciIsInVzZXJJZCI6IjEiLCJzdWIiOiJsYW9s" +
                "aW5AMTIzLmNvbSIsImlzcyI6IjA5OGY2YmNkNDYyMWQzNzNjYWRlNGU4MzI2MjdiNGY2IiwiaWF0IjoxNTk1NDIyOTg4LCJhdWQiOiJy" +
                "ZXN0YXBpdXNlciIsImV4cCI6MTU5NjAyNzc4OCwibmJmIjoxNTk1NDIyOTg4fQ.ZsC2NuJWkGILuFlMZN1__Doz7eT7HGDXp2c6KZrlJfc";

        Integer uid = Integer.valueOf(JwtTokenUtil.getUserId(token.substring(7), audience.getBase64Secret()));
        System.out.println(uid);
    }
}
