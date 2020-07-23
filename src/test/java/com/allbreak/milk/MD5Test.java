package com.allbreak.milk;

import com.allbreak.milk.utils.MD5Util;

/**
 * @ClassName MD5Test
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/16 18:17
 * @Version 1.0
 */

public class MD5Test {
    public static void main(String[] args) {
        // 生成随机盐
        String salt = MD5Util.getSalt();
        System.out.println(salt);

        // 加盐进行MD5加密
        String second = MD5Util.md5("123456" + salt);
        System.out.println(second);

        // 对比
        System.out.println( MD5Util.compare("123456", second, salt) );
    }
}
