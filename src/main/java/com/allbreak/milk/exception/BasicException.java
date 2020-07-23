package com.allbreak.milk.exception;

/**
 * @ClassName BasicException
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/16 23:02
 * @Version 1.0
 */
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BasicException extends RuntimeException{

    private Integer code;

    private String errorCode;

    private String msg;

    public BasicException(String msg) {
        super(msg);
    }

    public BasicException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

}
