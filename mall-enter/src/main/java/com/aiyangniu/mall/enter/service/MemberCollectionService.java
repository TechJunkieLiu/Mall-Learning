package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.bo.MemberProductCollection;
import org.springframework.data.domain.Page;

/**
 * 会员商品收藏管理接口
 *
 * @author lzq
 * @date 2023/07/05
 */
public interface MemberCollectionService {

    /**
     * 添加收藏
     *
     * @param productCollection 会员商品收藏
     * @return 添加数量
     */
    int add(MemberProductCollection productCollection);

    /**
     * 删除收藏
     *
     * @param productId 商品ID
     * @return 删除数量
     */
    int delete(Long productId);

    /**
     * 分页查询商品收藏
     *
     * @param pageNum 起始页
     * @param pageSize 页条数
     * @return 分页商品收藏列表
     */
    Page<MemberProductCollection> list(Integer pageNum, Integer pageSize);

    /**
     * 查看商品收藏详情
     *
     * @param productId 商品ID
     * @return 商品收藏详情
     */
    MemberProductCollection detail(Long productId);

    /**
     * 清空收藏
     */
    void clear();
}
