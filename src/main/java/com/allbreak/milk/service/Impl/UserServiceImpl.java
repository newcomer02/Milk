package com.allbreak.milk.service.Impl;

import com.allbreak.milk.entity.User;
import com.allbreak.milk.enums.ResultCode;
import com.allbreak.milk.enums.ResultEnum;
import com.allbreak.milk.exception.user.LoginException;
import com.allbreak.milk.repository.UserRepository;
import com.allbreak.milk.service.UserService;
import com.allbreak.milk.utils.MD5Util;
import com.allbreak.milk.validation.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/16 18:11
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User register(RegisterForm registerForm) throws ParseException{
        User user = new User();
        String password = registerForm.getPassword();
        String salt = MD5Util.getSalt();
        String newPassword = MD5Util.md5(password + salt);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createTime = format.format(date);

        user.setOpenid(salt);
        user.setAccount(registerForm.getAccount());
        user.setPassword(newPassword);
        user.setNickName(registerForm.getNickName());
        user.setCreateTime(format.parse(createTime));
        user.setLastLoginTime(format.parse(createTime));

        return userRepository.save(user);
    }

    @Override
    public User login(String account, String password) throws ParseException {
        User user = userRepository.findByAccount(account);
        // 账户是否存在
        if (user == null) {
            throw new LoginException(ResultCode.FAIL, ResultEnum.USER_NOT_EXIST);
        }

        // 密码是否正确
        String openId = user.getOpenid();
        if (!MD5Util.compare(password, user.getPassword(),openId)) {
            throw new LoginException(ResultCode.FAIL, ResultEnum.PASSWORD_NOT_EQUAL);
        }

        // 更新登录时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setLastLoginTime((format.parse(format.format(date))));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public boolean checkIsNickNameExist(String nickName) {
        User user = userRepository.findByNickName(nickName);
        return !(user == null);
    }

    @Override
    public boolean checkIsAccountExist(String account) {
        User user = userRepository.findByAccount(account);
        return !(user == null);
    }


}
