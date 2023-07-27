package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.pojo.UmsAdmin;
import com.aiyangniu.mall.enter.model.pojo.UmsResource;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户信息封装类
 *
 * @author lzq
 * @date 2023/04/25
 */
@Data
public class AdminUserDetails implements UserDetails {

    private static final long serialVersionUID = 6441872865170596643L;

    /** 后台用户 **/
    private final UmsAdmin umsAdmin;

    /** 拥有资源列表 **/
    private final List<UmsResource> resourceList;

    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsResource> resourceList) {
        this.umsAdmin = umsAdmin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户的角色
        return resourceList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}
