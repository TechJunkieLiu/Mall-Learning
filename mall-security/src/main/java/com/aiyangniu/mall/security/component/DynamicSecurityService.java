package com.aiyangniu.mall.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * 动态权限相关业务接口
 *
 * @author lzq
 * @date 2023/04/21
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应MAP
     *
     * @return 资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
