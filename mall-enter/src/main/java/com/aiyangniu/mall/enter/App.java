package com.aiyangniu.mall.enter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 *
 * @author lzq
 * @date 2023/04/21
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.aiyangniu.mall", exclude = {FlywayAutoConfiguration.class})
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        log.info("\n\t-----------------------------------" + "\n\t'{}' 运行成功！" + " 端口号：{}",
                context.getEnvironment().getProperty("spring.application.name"),
                context.getEnvironment().getProperty("server.port")
        );
    }
}
