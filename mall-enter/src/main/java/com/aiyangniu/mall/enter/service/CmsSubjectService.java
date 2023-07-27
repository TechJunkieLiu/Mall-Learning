package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.pojo.CmsSubject;

import java.util.List;

/**
 * 商品专题管理接口
 *
 * @author lzq
 * @date 2023/06/01
 */
public interface CmsSubjectService {

    /**
     * 查询所有专题
     *
     * @return 专题列表
     */
    List<CmsSubject> listAll();

    /**
     * 分页查询专题
     *
     * @param keyword 关键字
     * @param pageNum 当前页
     * @param pageSize 页条数
     * @return 专题列表
     */
    List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);
}
