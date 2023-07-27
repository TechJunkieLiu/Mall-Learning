package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.UmsAdminLoginLog;

/**
 * 用户登录日志Mapper
 *
 * @author lzq
 * @date 2023/04/25
 */
public interface UmsAdminLoginLogMapper {

    /**
     * 添加用户登录记录
     *
     * @param record 用户登录记录
     * @return 添加个数
     */
    int insert(UmsAdminLoginLog record);
}
