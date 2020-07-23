package com.allbreak.milk.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName Audience
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/16 22:41
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {

    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;

}
