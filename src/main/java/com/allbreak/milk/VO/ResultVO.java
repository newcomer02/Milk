package com.allbreak.milk.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @ClassName ResultVO
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/16 21:45
 * @Version 1.0
 */
@Data
// 该注解作用是实体类转换为json格式时，属性值为null的不参与序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    private Integer code;

    private String errorCode;

    private String msg;

    private T data;
}
