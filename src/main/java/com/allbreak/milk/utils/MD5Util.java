package com.allbreak.milk.utils;

import net.bytebuddy.utility.RandomString;
import org.springframework.util.DigestUtils;

/**
 * @ClassName MD5Util
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/16 18:16
 * @Version 1.0
 */
public class MD5Util {


    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public static String getSalt() {
        return RandomString.make(10);
    }

    public static boolean compare(String password, String DbPassWord, String salt) {
        return md5(password + salt).equals(DbPassWord);
    }

}
