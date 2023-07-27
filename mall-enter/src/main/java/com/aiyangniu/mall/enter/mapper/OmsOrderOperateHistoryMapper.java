package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.OmsOrderOperateHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作记录Mapper
 *
 * @author lzq
 * @date 2023/06/01
 */
public interface OmsOrderOperateHistoryMapper extends BaseMapper<OmsOrderOperateHistory> {

    /**
     * 批量创建
     *
     * @param orderOperateHistoryList 订单操作记录
     * @return 创建数量
     */
    int insertList(@Param("list") List<OmsOrderOperateHistory> orderOperateHistoryList);
}
