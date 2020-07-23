package com.allbreak.milk.exception;

import com.allbreak.milk.VO.ResultVO;
import com.allbreak.milk.enums.ResultCode;
import com.allbreak.milk.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @ClassName ExceptionHandler
 * @Description 统一处理异常
 * @Author Lin
 * @Date 2020/7/17 10:54
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handler(Exception e) {
        if (e instanceof BasicException) {
            // 业务逻辑异常
            return ResultVOUtil.error(((BasicException) e).getCode(), ((BasicException) e).getErrorCode(), e.getMessage());
        }
        else if (e instanceof ConstraintViolationException) {
            // 单个参数的校验失败，会抛出该异常，导致返回服务器异常信息，对此进行单独处理
            ConstraintViolationException cs = (ConstraintViolationException) e;
            String msg = cs.getMessage();
            String [] res = msg.split(" ");
            return ResultVOUtil.error(ResultCode.FAIL.getCode(), res[1]);
        }
        else {
            /***
             *如果是服务器内部错误，那么内部错误生成日志，
             * 同时返回客户端服务器内部错误，不暴露具体信息
             * */
            log.info(e.getMessage());
            return ResultVOUtil.error(ResultCode.INTERNAL_SERVER_ERROR.getCode(),ResultCode.INTERNAL_SERVER_ERROR.getMsg());
        }
    }
}
