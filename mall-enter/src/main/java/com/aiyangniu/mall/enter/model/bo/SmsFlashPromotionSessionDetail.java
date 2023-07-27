package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.pojo.SmsFlashPromotionSession;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 包含商品数量的场次信息
 *
 * @author lzq
 * @date 2023/06/15
 */
@Getter
@Setter
public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession {

    private static final long serialVersionUID = -1729640148452805868L;

    @ApiModelProperty("商品数量")
    private Long productCount;
}
