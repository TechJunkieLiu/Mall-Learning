package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.pojo.CmsPreferenceArea;

import java.util.List;

/**
 * 商品优选管理接口
 *
 * @author lzq
 * @date 2023/05/13
 */
public interface CmsPreferenceAreaService {

    /**
     * 获取所有优选专区
     *
     * @return 优选专区
     */
    List<CmsPreferenceArea> listAll();
}
