package com.allbreak.milk;

import com.allbreak.milk.entity.User;
import com.allbreak.milk.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName UserRepositoryTest
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/16 18:06
 * @Version 1.0
 */
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserService userService;

    @Test
    public void userInsertTest() throws ParseException {
        String salt = "lLYBhxyr8b";
        String newPassword = "a4ed8061b106d730c4345449da7e369d";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createTime = format.format(date);

        User user = new User();
        user.setOpenid(salt);
        user.setAccount("laolin@123.com");
        user.setPassword(newPassword);
        user.setNickName("老林");
        user.setCreateTime(format.parse(createTime));
        user.setLastLoginTime(format.parse(createTime));

        try {
            // Assertions.assertNotNull(userService.register(user));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
