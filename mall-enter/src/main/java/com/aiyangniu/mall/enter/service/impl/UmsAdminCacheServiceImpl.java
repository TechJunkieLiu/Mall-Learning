package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.aiyangniu.mall.common.service.RedisService;
import com.aiyangniu.mall.enter.mapper.UmsAdminRoleRelationMapper;
import com.aiyangniu.mall.enter.model.pojo.UmsAdmin;
import com.aiyangniu.mall.enter.model.pojo.UmsAdminRoleRelation;
import com.aiyangniu.mall.enter.model.pojo.UmsResource;
import com.aiyangniu.mall.enter.service.UmsAdminCacheService;
import com.aiyangniu.mall.enter.service.UmsAdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户缓存操作实现类
 *
 * @author lzq
 * @date 2023/04/25
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {

    private final RedisService redisService;
    private final UmsAdminService umsAdminService;
    private final UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Value("${redis.database}")
    private String redisDatabase;

    @Value("${redis.expire.common}")
    private Long redisExpire;

    @Value("${redis.key.admin}")
    private String redisKeyAdmin;

    @Value("${redis.key.resourceList}")
    private String redisKeyResourceList;

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        String key = redisDatabase + ":" + redisKeyResourceList + ":" + adminId;
        return (List<UmsResource>) redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {
        String key = redisDatabase + ":" + redisKeyResourceList + ":" + adminId;
        redisService.set(key, resourceList, redisExpire);
    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = redisDatabase + ":" + redisKeyAdmin + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = redisDatabase + ":" + redisKeyAdmin + ":" + admin.getUsername();
        redisService.set(key, admin, redisExpire);
    }

    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = umsAdminService.getItem(adminId);
        if (admin != null) {
            String key = redisDatabase + ":" + redisKeyAdmin + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long id) {
        String key = redisDatabase + ":" + redisKeyResourceList + ":" + id;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {
        List<UmsAdminRoleRelation> relationList = umsAdminRoleRelationMapper.selectList(new LambdaQueryWrapper<UmsAdminRoleRelation>().eq(UmsAdminRoleRelation::getRoleId, roleId));
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = redisDatabase + ":" + redisKeyResourceList + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        List<UmsAdminRoleRelation> relationList = umsAdminRoleRelationMapper.selectList(new LambdaQueryWrapper<UmsAdminRoleRelation>().in(UmsAdminRoleRelation::getRoleId, roleIds));
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = redisDatabase + ":" + redisKeyResourceList + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
        List<Long> adminIdList = umsAdminRoleRelationMapper.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            String keyPrefix = redisDatabase + ":" + redisKeyResourceList + ":";
            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }
}
