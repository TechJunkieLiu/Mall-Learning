package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.common.service.RedisService;
import com.aiyangniu.mall.enter.mapper.UmsMemberMapper;
import com.aiyangniu.mall.enter.model.pojo.UmsMember;
import com.aiyangniu.mall.enter.service.UmsMemberCacheService;
import com.aiyangniu.mall.security.annotation.CacheException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 会员信息缓存实现类
 *
 * @author lzq
 * @date 2023/04/28
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsMemberCacheServiceImpl implements UmsMemberCacheService {

    private final RedisService redisService;
    private final UmsMemberMapper umsMemberMapper;

    @Value("${redis.database}")
    private String redisDatabase;

    @Value("${redis.key.authCode}")
    private String redisKeyAuthCode;

    @Value("${redis.key.member}")
    private String redisKeyMember;

    @Value("${redis.expire.common}")
    private Long redisExpire;

    @Value("${redis.expire.authCode}")
    private Long redisExpireAuthCode;

    @CacheException
    @Override
    public String getAuthCode(String telephone) {
        String key = redisDatabase + ":" + redisKeyAuthCode + ":" + telephone;
        return (String) redisService.get(key);
    }

    @CacheException
    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = redisDatabase + ":" + redisKeyAuthCode + ":" + telephone;
        redisService.set(key, authCode, redisExpireAuthCode);
    }

    @Override
    public UmsMember getMember(String username) {
        String key = redisDatabase + ":" + redisKeyMember + ":" + username;
        return (UmsMember) redisService.get(key);
    }

    @Override
    public void setMember(UmsMember member) {
        String key = redisDatabase + ":" + redisKeyMember + ":" + member.getUsername();
        redisService.set(key, member, redisExpire);
    }

    @Override
    public void delMember(Long memberId) {
        UmsMember umsMember = umsMemberMapper.selectById(memberId);
        if (!ObjectUtils.isEmpty(umsMember)) {
            String key = redisDatabase + ":" + redisKeyMember + ":" + umsMember.getUsername();
            redisService.del(key);
        }
    }
}
