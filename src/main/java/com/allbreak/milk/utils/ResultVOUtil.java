package com.allbreak.milk.utils;

import com.allbreak.milk.VO.ResultVO;
import com.allbreak.milk.enums.ResultCode;

/**
 * @ClassName ResultVOUtil
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/17 12:00
 * @Version 1.0
 */
public class ResultVOUtil {

    public static ResultVO success() {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(ResultCode.SUCCESS.getCode());
        resultVO.setMsg(ResultCode.SUCCESS.getMsg());
        return resultVO;
    }

    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(ResultCode.SUCCESS.getCode());
        resultVO.setMsg(ResultCode.SUCCESS.getMsg());
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO error(Integer code, String errorCode, String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setErrorCode(errorCode);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
