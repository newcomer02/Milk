package com.allbreak.milk;

import com.allbreak.milk.component.Audience;
import com.allbreak.milk.entity.User;
import com.allbreak.milk.service.UserService;
import com.allbreak.milk.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName UserServiceTest
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/17 14:57
 * @Version 1.0
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    Audience audience;

    @Test
    public void test1() {
        String ac = "laolin@123.com";
        String pa = "123456";

        try {
            User user = userService.login(ac, pa);

            String token = JwtTokenUtil.createJWT(user.getId().toString(), user.getAccount(), "user", audience);
            System.out.println(token);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
