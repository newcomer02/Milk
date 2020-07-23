package com.allbreak.milk.controller;

import com.alibaba.fastjson.JSON;
import com.allbreak.milk.VO.ResultVO;
import com.allbreak.milk.annotation.JwtIgnore;
import com.allbreak.milk.component.Audience;
import com.allbreak.milk.entity.User;
import com.allbreak.milk.enums.ExpireEnum;
import com.allbreak.milk.enums.ResultCode;
import com.allbreak.milk.enums.ResultEnum;
import com.allbreak.milk.service.UserService;
import com.allbreak.milk.utils.JwtTokenUtil;
import com.allbreak.milk.utils.RedisUtil;
import com.allbreak.milk.utils.ResultVOUtil;
import com.allbreak.milk.validation.BasicValidation;
import com.allbreak.milk.validation.LoginForm;
import com.allbreak.milk.validation.RegisterForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/17 13:44
 * @Version 1.0
 */
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Audience audience;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    @JwtIgnore
    public ResultVO login(HttpServletResponse response, @Valid LoginForm loginForm, BindingResult bindingResult) throws Exception{
        ResultVO resultVO = BasicValidation.checkDataValidate(bindingResult);
        if (resultVO != null) return resultVO;

        String account = loginForm.getAccount();
        String password = loginForm.getPassword();

        User user = userService.login(account, password);

        String token = JwtTokenUtil.createJWT(user.getId().toString(), user.getAccount(), "user", audience);
        log.info("登录成功####，token = {}", token);

        redisUtil.set(user.getId().toString(), JSON.toJSONString(user), ExpireEnum.LOGIN_EXPIRE.getTime());

        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        return ResultVOUtil.success(user);
    }

    @PostMapping("/register")
    @JwtIgnore
    public ResultVO register(@Valid RegisterForm registerForm, BindingResult bindingResult) throws Exception {
        ResultVO resultVO = BasicValidation.checkDataValidate(bindingResult);
        if (resultVO != null) return resultVO;

        // 将密码加密之后存入数据库
        User user = userService.register(registerForm);
        if (user == null) {
            throw new RuntimeException("用户创建失败");
        }
        return ResultVOUtil.success(user);
    }

    @GetMapping("/register/checkemail")
    @JwtIgnore
    public ResultVO checkAccount(@Email(message = "邮箱格式不合法") String email){
        if(!userService.checkIsAccountExist(email)) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultCode.FAIL.getCode(), ResultEnum.ACCOUNT_ALREADY_EXIST.getErroCode(),ResultEnum.ACCOUNT_ALREADY_EXIST.getMessage());
    }

    @GetMapping("/register/checkname")
    @JwtIgnore
    public ResultVO checkName(String nickname) {
        if (!userService.checkIsNickNameExist(nickname)) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultCode.FAIL.getCode(), ResultEnum.NICKNAME_ALREADY_EXIST.getErroCode(),ResultEnum.NICKNAME_ALREADY_EXIST.getMessage());
    }

    @GetMapping("/info")
    // 用于测试登录状态的接口，无实际意义
    public ResultVO info() {
        log.info("查看用户信息###");
        return ResultVOUtil.success();
    }
}
