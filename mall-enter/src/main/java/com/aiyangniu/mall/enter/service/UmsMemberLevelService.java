package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.pojo.UmsMemberLevel;

import java.util.List;

/**
 * 会员等级管理接口
 *
 * @author lzq
 * @date 2023/06/15
 */
public interface UmsMemberLevelService {

    /**
     * 获取所有会员等级
     *
     * @param defaultStatus 是否为默认会员
     * @return 会员等级列表
     */
    List<UmsMemberLevel> list(Integer defaultStatus);
}
