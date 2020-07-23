package com.allbreak.milk.service;

import com.allbreak.milk.entity.User;
import com.allbreak.milk.validation.RegisterForm;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/16 18:08
 * @Version 1.0
 */

public interface UserService {
    User register(RegisterForm registerForm) throws Exception;

    User login(String account, String password) throws Exception;

    boolean checkIsNickNameExist(String nickName);

    boolean checkIsAccountExist(String account);
}
