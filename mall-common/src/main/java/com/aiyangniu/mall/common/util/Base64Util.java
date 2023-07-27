package com.aiyangniu.mall.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Base64工具类
 *
 * @author lzq
 * @date 2023/06/12
 */
public class Base64Util {

    /**
     * BASE64加密
     */
    public static String encoder(String str){
        BASE64Encoder base64E = new BASE64Encoder();
        return base64E.encode(str.getBytes());
    }

    /**
     * BASE64解密
     */
    public static String decoder(String str) {
        BASE64Decoder base64D = new BASE64Decoder();
        byte[] bytes = null;
        try {
            bytes = base64D.decodeBuffer(str);
        }catch (IOException e){
            e.printStackTrace();
        }
        assert bytes != null;
        return new String(bytes);
    }
}
