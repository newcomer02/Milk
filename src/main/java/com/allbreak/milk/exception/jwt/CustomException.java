package com.allbreak.milk.exception.jwt;

import com.allbreak.milk.enums.ResultCode;
import com.allbreak.milk.enums.ResultEnum;
import com.allbreak.milk.exception.BasicException;
import lombok.Getter;

/**
 * @ClassName CustomException
 * @Description Jwt 异常类
 * @Author Lin
 * @Date 2020/7/16 23:08
 * @Version 1.0
 */
@Getter
public class CustomException extends BasicException {

    private Integer code;

    private String errorCode;

    public CustomException(ResultCode resultCode, ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultCode.getCode();
        this.errorCode = resultEnum.getErroCode();
    }
}
