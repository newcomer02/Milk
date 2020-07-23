package com.allbreak.milk.validation;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName LoginForm
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/17 17:42
 * @Version 1.0
 */
@Data
public class LoginForm {

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    public String account;

    @NotBlank(message = "密码不能为空")
    public String password;
}
