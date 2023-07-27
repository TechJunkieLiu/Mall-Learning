package com.aiyangniu.mall.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * IP地址工具类
 *
 * @author lzq
 * @date 2023/06/12
 */
public class IPUtil {

    /**
     * Window
     */
    public static String getLocalWindowIp() throws Exception {
        if (isWindowsOs()){
            return Inet4Address.getLocalHost().getHostAddress();
        }else {
            return getLocalLinuxIp();
        }
    }

    /**
     * Linux
     */
    private static String getLocalLinuxIp() {
        String ip = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements() ; ){
                NetworkInterface network = en.nextElement();
                String name = network.getName();
                if (!name.contains("docker") && !name.contains("lo")){
                    for (Enumeration<InetAddress> enumIpAddr = network.getInetAddresses(); enumIpAddr.hasMoreElements() ; ){
                        InetAddress address = enumIpAddr.nextElement();
                        if (!address.isLoopbackAddress()){
                            String ipAddress = address.getHostAddress();
                            if (!ipAddress.contains("::") && !ipAddress.contains("0:0:") && !ipAddress.contains("fe80")){
                                ip = ipAddress;
                                System.out.println(ipAddress);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ip;
    }

    private static boolean isWindowsOs(){
        boolean isWindowsOs = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")){
            isWindowsOs = true;
        }
        return isWindowsOs;
    }
}
