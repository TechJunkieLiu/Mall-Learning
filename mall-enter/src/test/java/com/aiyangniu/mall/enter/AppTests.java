//package com.aiyangniu.mall.enter;
//
//import com.aiyangniu.mall.common.util.SecretUtil;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class AppTests {
//
//    @Test
//    public void contextLoads() {
//        String origin = "http://192.168.61.100/gateway/console/fs/file/download";
//        String key = "1234567898765432";
//
//        String string1 = SecretUtil.mdEncode(origin);
//        String string2 = SecretUtil.digest(origin);
//        String string3 = SecretUtil.base64Encode(origin, true);
//        String string4 = SecretUtil.base64Encode(origin, false);
//        String string5 = SecretUtil.symmetric(origin, key, true);
//        String string6 = SecretUtil.symmetric(origin, key, false);
//        String string7 = SecretUtil.asymmetric(origin, true);
//        String string8 = SecretUtil.asymmetric(origin, false);
//        String string9 = SecretUtil.sm3(origin);
//        String string10 = SecretUtil.sm4(origin, true);
//        String string11 = SecretUtil.sm4(origin, false);
//        String string12 = SecretUtil.sm4(origin, key, true);
//        String string13 = SecretUtil.sm4(origin, key, false);
//
//        System.out.println("==========mdEncode==========" + string1);
//        System.out.println("==========digest==========" + string2);
//        System.out.println("==========base64Encode==========" + string3);
//        System.out.println("==========base64Encode==========" + string4);
//        System.out.println("==========symmetric==========" + string5);
//        System.out.println("==========symmetric==========" + string6);
//        System.out.println("==========asymmetric==========" + string7);
//        System.out.println("==========asymmetric==========" + string8);
//        System.out.println("==========sm3==========" + string9);
//        System.out.println("==========sm4==========" + string10);
//        System.out.println("==========sm4==========" + string11);
//        System.out.println("==========sm4==========" + string12);
//        System.out.println("==========sm4==========" + string13);
//    }
//}
