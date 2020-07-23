package com.allbreak.milk.validation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @ClassName Register
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/17 23:36
 * @Version 1.0
 */
@Data
public class RegisterForm {

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String account;

    @NotBlank(message = "昵称不能为空")
    @Length(min = 4, max = 10, message = "昵称长度不合法")
    private String nickName;

    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", message = "密码至少含有1位小写字母的且至少为8位数")
    private String password;
}
