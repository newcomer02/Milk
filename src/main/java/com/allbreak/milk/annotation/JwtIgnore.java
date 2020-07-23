package com.allbreak.milk.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
// 指定注解的合法位置：METHOD 是指方法上，还有TYPE，FIELD等
@Retention(RetentionPolicy.RUNTIME)
// 指定注解的保留策略
@Documented
// 表明这个注解被javadoc 记录
public @interface JwtIgnore {
}
