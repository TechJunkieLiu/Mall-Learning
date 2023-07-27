package com.aiyangniu.mall.enter.mapper;

import com.aiyangniu.mall.enter.model.pojo.PmsProductVertifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品审核日志管理Mapper
 *
 * @author lzq
 * @date 2023/05/31
 */
public interface PmsProductVertifyRecordMapper {

    /**
     * 批量创建
     *
     * @param list 商品审核日志列表
     * @return 创建个数
     */
    int insertList(@Param("list") List<PmsProductVertifyRecord> list);
}
