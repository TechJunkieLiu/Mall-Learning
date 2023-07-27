package com.aiyangniu.mall.security.annotation;

import java.lang.annotation.*;

/**
 * 自定义缓存异常注解，有该注解的缓存方法会抛出异常（比如验证码存储接口，如果期间Redis宕机了，我们需要的是报错而不是执行成功）
 *
 * @author lzq
 * @date 2023/04/21
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
