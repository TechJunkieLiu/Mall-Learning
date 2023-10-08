package com.aiyangniu.mall.enter.config;

import com.aiyangniu.mall.common.config.BaseSwaggerConfig;
import com.aiyangniu.mall.common.domain.SwaggerProperties;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger自定义配置
 *
 * @author lzq
 * @date 2023/04/28
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.aiyangniu.mall.enter.controller")
                .title("Mall系统")
                .description("Mall相关接口文档")
                .contactName("aiyangniu")
                .version("1.0.0")
                .enableSecurity(true)
                .build();
    }

    @Bean
    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return generateBeanPostProcessor();
    }
}
