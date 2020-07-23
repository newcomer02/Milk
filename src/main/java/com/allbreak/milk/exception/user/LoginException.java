package com.allbreak.milk.exception.user;

import com.allbreak.milk.enums.ResultCode;
import com.allbreak.milk.enums.ResultEnum;
import com.allbreak.milk.exception.BasicException;
import lombok.Getter;

/**
 * @ClassName LoginException
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/17 12:28
 * @Version 1.0
 */
@Getter
public class LoginException extends BasicException {

    private Integer code;

    private String errorCode;

    public LoginException(ResultCode resultCode, ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultCode.getCode();
        this.errorCode = resultEnum.getErroCode();
    }
}
