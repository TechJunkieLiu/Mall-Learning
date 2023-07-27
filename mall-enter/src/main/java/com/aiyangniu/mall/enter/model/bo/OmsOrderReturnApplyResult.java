package com.aiyangniu.mall.enter.model.bo;

import com.aiyangniu.mall.enter.model.pojo.OmsCompanyAddress;
import com.aiyangniu.mall.enter.model.pojo.OmsOrderReturnApply;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请信息封装
 *
 * @author lzq
 * @date 2023/06/01
 */
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {

    private static final long serialVersionUID = -7387977404916836014L;

    @Getter
    @Setter
    @ApiModelProperty(value = "公司收货地址")
    private OmsCompanyAddress companyAddress;

    @Override
    public String toString() {
        return "OmsOrderReturnApplyResultDTO{" +
                "companyAddress=" + companyAddress +
                '}';
    }
}
