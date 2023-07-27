package com.aiyangniu.mall.security.config;

import com.aiyangniu.mall.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 *
 * @author lzq
 * @date 2023/04/21
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
