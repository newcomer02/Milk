package com.allbreak.milk.enums;

import lombok.Getter;

@Getter
public enum ResultCode {

    // 200 成功
    SUCCESS(200, "success"),

    // 400 失败
    FAIL(400, "fail"),

    // 401 未认证
    UNAUTHORIZED(401),

    // 404 未找到
    NOT_FOUND(404),

    // 500 服务器内部错误
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

  private Integer code;

    private String msg;

    ResultCode(Integer code) {
        this.code = code;
    }

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
