package com.aiyangniu.mall.common.util;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 获取IP工具类
 *
 * @author lzq
 * @date 2023/08/14
 */
public class IpAddressUtil {

    private static final String POINT = ",";
    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_IPV4  = "127.0.0.1";
    private static final String LOCALHOST_IPV6  = "0:0:0:0:0:0:0:1";
    private static final Logger LOGGER = LoggerFactory.getLogger(IpAddressUtil.class);

    /**
     * 通过Request获取请求真实IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        // 通过HTTP代理服务器转发时添加
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            // 从本地访问时根据网卡取本机配置的IP
            if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                assert inetAddress != null;
                ipAddress = inetAddress.getHostAddress();
            }
        }
        // 通过多个代理转发的情况，第一个IP为客户端真实IP，多个IP会按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(POINT) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 通过Request获取请求真实IP地址（场景：向不同省份的用户展示不同的内容）
     */
    public static String getIpAddressOther(HttpServletRequest request){
        String xip = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");
        if (StrUtil.isNotEmpty(xFor) && !UNKNOWN.equalsIgnoreCase(xFor)){
            // 多次反向代理后会有多个IP值，第一个IP才是真实IP（X-Forwarded-For: client, proxy1, proxy2, proxy...）
            int index = xFor.indexOf(POINT);
            if (index != -1){
                return xFor.substring(0, index);
            }else {
                return xFor;
            }
        }
        xFor = xip;
        if (StrUtil.isNotEmpty(xFor) && !UNKNOWN.equalsIgnoreCase(xFor)){
            return xFor;
        }
        if (StrUtil.isBlank(xFor) || UNKNOWN.equalsIgnoreCase(xFor)){
            xFor = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(xFor) || UNKNOWN.equalsIgnoreCase(xFor)){
            xFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(xFor) || UNKNOWN.equalsIgnoreCase(xFor)){
            xFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StrUtil.isBlank(xFor) || UNKNOWN.equalsIgnoreCase(xFor)){
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StrUtil.isBlank(xFor) || UNKNOWN.equalsIgnoreCase(xFor)){
            xFor = request.getRemoteAddr();
        }
        return xFor;
    }

    /**
     * 获取本机IP地址
     */
    public static String getLocalIpAddress() throws Exception {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                // 对网络接口进行筛选排除：回送接口、虚拟网卡、未在使用中
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()){
                    continue;
                }else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()){
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address){
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        }catch (Exception e){
            LOGGER.error("IP地址获取失败：{}", e.toString());
        }
        return "";
    }
}
