package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.pojo.CmsSubject;
import com.aiyangniu.mall.enter.model.pojo.PmsBrand;
import com.aiyangniu.mall.enter.model.pojo.PmsProduct;
import com.aiyangniu.mall.enter.model.pojo.SmsHomeAdvertise;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 首页内容返回信息封装
 *
 * @author lzq
 * @date 2023/06/08
 */
@Getter
@Setter
public class HomeContentResult {

    @ApiModelProperty("轮播广告")
    private List<SmsHomeAdvertise> advertiseList;

    @ApiModelProperty("推荐品牌")
    private List<PmsBrand> brandList;

    @ApiModelProperty("当前秒杀场次")
    private HomeFlashPromotion homeFlashPromotion;

    @ApiModelProperty("新品推荐")
    private List<PmsProduct> newProductList;

    @ApiModelProperty("人气推荐")
    private List<PmsProduct> hotProductList;

    @ApiModelProperty("推荐专题")
    private List<CmsSubject> subjectList;
}
