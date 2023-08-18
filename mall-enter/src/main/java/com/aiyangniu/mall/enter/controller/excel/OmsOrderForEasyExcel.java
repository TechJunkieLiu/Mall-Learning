package com.aiyangniu.mall.enter.controller.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据导出的Excel模板实体
 *
 * @author lzq
 * @date 2023/08/18
 */
@Data
@TableName(value = "oms_order")
public class OmsOrderForEasyExcel implements Serializable {

    private static final long serialVersionUID = 2795165848228532381L;

    @ColumnWidth(10)
    @ExcelProperty(value = "订单ID", index = 0)
    private Long id;

    @ColumnWidth(15)
    @ExcelProperty(value = "会员ID", index = 1)
    private Long memberId;

    @ColumnWidth(20)
    @ExcelProperty(value = "优惠券ID", index = 2)
    private Long couponId;

    @ColumnWidth(25)
    @ExcelProperty(value = "订单编号", index = 3)
    private String orderSn;

    @ColumnWidth(30)
    @ExcelProperty(value = "提交时间", index = 4)
    private Date createTime;

    @ExcelProperty(value = "用户帐号", index = 5)
    private String memberUsername;

    @ExcelProperty(value = "订单总金额", index = 6)
    private BigDecimal totalAmount;

    @ExcelProperty(value = "应付金额", index = 7)
    private BigDecimal payAmount;

    @ExcelProperty(value = "运费金额", index = 8)
    private BigDecimal freightAmount;

    @ExcelProperty(value = "促销优化金额", index = 9)
    private BigDecimal promotionAmount;

    @ExcelProperty(value = "积分抵扣金额", index = 10)
    private BigDecimal integrationAmount;

    @ExcelProperty(value = "优惠券抵扣金额", index = 11)
    private BigDecimal couponAmount;

    @ExcelProperty(value = "管理员后台调整订单使用的折扣金额", index = 12)
    private BigDecimal discountAmount;

    @ExcelProperty(value = "支付方式", index = 13)
    private Integer payType;

    @ExcelProperty(value = "订单来源", index = 14)
    private Integer sourceType;

    @ExcelProperty(value = "订单状态", index = 15)
    private Integer status;

    @ExcelProperty(value = "订单类型", index = 16)
    private Integer orderType;

    @ExcelProperty(value = "物流公司", index = 17)
    private String deliveryCompany;

    @ExcelProperty(value = "物流单号", index = 18)
    private String deliverySn;

    @ExcelProperty(value = "自动确认时间（天）", index = 19)
    private Integer autoConfirmDay;

    @ExcelProperty(value = "可以获得的积分", index = 20)
    private Integer integration;

    @ExcelProperty(value = "可以活动的成长值", index = 21)
    private Integer growth;

    @ExcelProperty(value = "活动信息", index = 22)
    private String promotionInfo;

    @ExcelProperty(value = "发票类型", index = 23)
    private Integer billType;

    @ExcelProperty(value = "发票抬头", index = 24)
    private String billHeader;

    @ExcelProperty(value = "发票内容", index = 25)
    private String billContent;

    @ExcelProperty(value = "收票人电话", index = 26)
    private String billReceiverPhone;

    @ExcelProperty(value = "收票人邮箱", index = 27)
    private String billReceiverEmail;

    @ExcelProperty(value = "收货人姓名", index = 28)
    private String receiverName;

    @ExcelProperty(value = "收货人电话", index = 29)
    private String receiverPhone;

    @ExcelProperty(value = "收货人邮编", index = 30)
    private String receiverPostCode;

    @ExcelProperty(value = "省份/直辖市", index = 31)
    private String receiverProvince;

    @ExcelProperty(value = "城市", index = 32)
    private String receiverCity;

    @ExcelProperty(value = "区", index = 33)
    private String receiverRegion;

    @ExcelProperty(value = "详细地址", index = 34)
    private String receiverDetailAddress;

    @ExcelProperty(value = "订单备注", index = 35)
    private String note;

    @ExcelProperty(value = "确认收货状态", index = 36)
    private Integer confirmStatus;

    @ExcelProperty(value = "删除状态", index = 37)
    private Integer deleteStatus;

    @ExcelProperty(value = "下单时使用的积分", index = 38)
    private Integer useIntegration;

    @ExcelProperty(value = "支付时间", index = 39)
    private Date paymentTime;

    @ExcelProperty(value = "发货时间", index = 40)
    private Date deliveryTime;

    @ExcelProperty(value = "确认收货时间", index = 41)
    private Date receiveTime;

    @ExcelProperty(value = "评价时间", index = 42)
    private Date commentTime;

    @ExcelProperty(value = "修改时间", index = 43)
    private Date modifyTime;
}
