package com.aiyangniu.mall.enter.model.bo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * FlywayDB
 *
 * @author lzq
 * @date 2023/06/25
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "spring")
public class FlywayBean {

    private Map<String, String> datasource;

    public Map<String, String> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, String> datasource) {
        this.datasource = datasource;
    }

    public FlywayBean() {
    }

    public FlywayBean(Map<String,String> datasource) {
        this.datasource = datasource;
    }
}
