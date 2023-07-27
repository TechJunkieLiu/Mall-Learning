package com.aiyangniu.mall.enter.config;

import com.aiyangniu.mall.enter.model.pojo.UmsResource;
import com.aiyangniu.mall.enter.service.UmsAdminService;
import com.aiyangniu.mall.enter.service.UmsResourceService;
import com.aiyangniu.mall.security.component.DynamicSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mall-security模块相关配置
 *
 * @author lzq
 * @date 2023/05/25
 */
@Configuration
public class MallSecurityConfig {

//    @Autowired
//    private UmsMemberService umsMemberService;

    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UmsResourceService umsResourceService;

    @Bean
    public UserDetailsService userDetailsServiceAdmin() {
        // 获取登录用户信息
        return username -> umsAdminService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>(16);
                List<UmsResource> resourceList = umsResourceService.listAll();
                for (UmsResource resource : resourceList) {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                return map;
            }
        };
    }
}
