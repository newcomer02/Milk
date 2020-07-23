package com.allbreak.milk.enums;

import lombok.Getter;

@Getter
public enum ExpireEnum {

    // 登录过期时间
    LOGIN_EXPIRE(3600 * 7 * 24)

    ;
    private Integer time;

    ExpireEnum(Integer time) {
        this.time = time;
    }
}
