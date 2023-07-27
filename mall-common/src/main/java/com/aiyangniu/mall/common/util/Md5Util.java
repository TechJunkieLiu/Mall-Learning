package com.aiyangniu.mall.common.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 工具类
 *
 * @author lzq
 * @date 2023/06/12
 */
public class Md5Util {

    /**
     * MD5加密
     */
    public static String encode(String message){
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] bytes = md.digest(message.getBytes());
            return new BASE64Encoder().encode(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
