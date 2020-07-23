package com.allbreak.milk.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {


    // 400 用户
    USER_NOT_EXIST("100001", "账户不存在"),
    PASSWORD_NOT_EQUAL("100002", "密码不正确"),
    USER_NOT_LOGGED_IN("100003", "用户未登录，请先登录"),
    NICKNAME_ALREADY_EXIST("100004", "用户名已存在"),
    ACCOUNT_ALREADY_EXIST("100005", "账户已经存在"),
    FILE_NOT_NULL("100006", "上传的文件不能为空"),
    VERIFYCODE_EXPIRED("100007","验证码过期"),
    VERIFYCODE_ERROR("100008", "验证码错误"),
    // 秒杀
    SECKILL_LINE_UP("500100", "排队中"),
    SECKILL_NO_QUOTE("500101", "课程名额已满，祝您下次好运"),
    SECKILL_BOUGHT("500102", "您已购买该课程，请前往订单页面查看"),
    SECKILL_PATH_ERROR("500104", "请求地址错误，请联系客服人员"),

    // 401 签名错误
    PERMISSION_TOKEN_EXPIRED("200001", "Token过期"),
    PERMISSION_TOKEN_INVALID("200002", "Token解析异常"),
    PERMISSION_SIGNATURE_ERROR("200003", "签名失败"),

    // 402 redis缓存

    ;

    private String erroCode;

    private String message;

    ResultEnum(String erroCode, String message) {
        this.erroCode = erroCode;
        this.message = message;
    }
}
