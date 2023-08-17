package com.aiyangniu.mall.enter.controller.excel;

import com.aiyangniu.mall.common.util.ExcelUtils.poi.ExcelExport;
import com.aiyangniu.mall.common.util.ExcelUtils.poi.ExcelImport;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 导入订单模板对象
 *
 * @author lzq
 * @date 2023/08/16
 */
@Data
public class OmsOrder {

    /** 行号 **/
    @TableField(exist = false)
    private int rowNum;

    /** 行数据 **/
    @TableField(exist = false)
    private String rowData;

    /** 行错误提示 **/
    @TableField(exist = false)
    private String rowTips;

    @ExcelImport(value = "订单ID", required = true)
    @ExcelExport(value = "订单ID", sort = 1, example = "1")
    private Long id;

    @ExcelImport(value = "会员ID", required = true)
    @ExcelExport(value = "会员ID", sort = 2, example = "1")
    private Long memberId;

    @ExcelImport("优惠券ID")
    @ExcelExport(value = "优惠券ID", sort = 3, example = "2")
    private Long couponId;

    @ExcelImport("订单编号")
    @ExcelExport(value = "订单编号", example = "201809150101000001")
    private String orderSn;

    @ExcelImport("提交时间")
    @ExcelExport(value = "提交时间", example = "2018-09-15 12:24:27")
    private Date createTime;

    @ExcelImport("用户帐号")
    @ExcelExport(value = "用户帐号", example = "test")
    private String memberUsername;

    @ExcelImport("订单总金额")
    @ExcelExport(value = "订单总金额", example = "18732.00")
    private BigDecimal totalAmount;

    @ExcelImport("应付金额")
    @ExcelExport(value = "应付金额", example = "16377.75")
    private BigDecimal payAmount;

    @ExcelImport("运费金额")
    @ExcelExport(value = "运费金额", example = "20.00")
    private BigDecimal freightAmount;

    @ExcelImport("促销优化金额")
    @ExcelExport(value = "促销优化金额", example = "2344.25")
    private BigDecimal promotionAmount;

    @ExcelImport("积分抵扣金额")
    @ExcelExport(value = "积分抵扣金额", example = "0.00")
    private BigDecimal integrationAmount;

    @ExcelImport("优惠券抵扣金额")
    @ExcelExport(value = "优惠券抵扣金额", example = "10.00")
    private BigDecimal couponAmount;

    @ExcelImport("管理员后台调整订单使用的折扣金额")
    @ExcelExport(value = "管理员后台调整订单使用的折扣金额", example = "10.00")
    private BigDecimal discountAmount;

    @ExcelImport(value = "支付方式", kv = "0-未支付;1-支付宝;2-微信")
    @ExcelExport(value = "支付方式", kv = "0-未支付;1-支付宝;2-微信", example = "0")
    private Integer payType;

    @ExcelImport(value = "订单来源", kv = "0-PC订单;1-app订单")
    @ExcelExport(value = "订单来源", kv = "0-PC订单;1-app订单", example = "1")
    private Integer sourceType;

    @ExcelImport(value = "订单状态", kv = "0-待付款;1-待发货;2-已发货;3-已完成;4-已关闭;5-无效订单")
    @ExcelExport(value = "订单状态", kv = "0-待付款;1-待发货;2-已发货;3-已完成;4-已关闭;5-无效订单", example = "4")
    private Integer status;

    @ExcelImport(value = "订单类型", kv = "0-正常订单;1-秒杀订单")
    @ExcelExport(value = "订单类型", kv = "0-正常订单;1-秒杀订单", example = "0")
    private Integer orderType;

    @ExcelImport("物流公司")
    @ExcelExport(value = "物流公司", example = "顺丰快递")
    private String deliveryCompany;

    @ExcelImport("物流单号")
    @ExcelExport(value = "物流单号", example = "201707196398345")
    private String deliverySn;

    @ExcelImport("自动确认时间（天）")
    @ExcelExport(value = "自动确认时间（天）", example = "15")
    private Integer autoConfirmDay;

    @ExcelImport("可以获得的积分")
    @ExcelExport(value = "可以获得的积分", example = "13284")
    private Integer integration;

    @ExcelImport("可以活动的成长值")
    @ExcelExport(value = "可以活动的成长值", example = "13284")
    private Integer growth;

    @ExcelImport("活动信息")
    @ExcelExport(value = "活动信息", example = "单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠")
    private String promotionInfo;

    @ExcelImport(value = "发票类型", kv = "0-不开发票;1-电子发票;2-纸质发票")
    @ExcelExport(value = "发票类型", kv = "0-不开发票;1-电子发票;2-纸质发票", example = "1")
    private Integer billType;

    @ExcelImport("发票抬头")
    @ExcelExport(value = "发票抬头", example = "内蒙古爱养牛科技有限公司")
    private String billHeader;

    @ExcelImport("发票内容")
    @ExcelExport(value = "发票内容", example = "增值税专用发票")
    private String billContent;

    @ExcelImport(value = "收票人电话", maxLength = 11)
    @ExcelExport(value = "收票人电话", example = "15827596636")
    private String billReceiverPhone;

    @ExcelImport("收票人邮箱")
    @ExcelExport(value = "收票人邮箱", example = "15827596636@139.com")
    private String billReceiverEmail;

    /** 联合唯一性验证 **/
    @ExcelImport(value = "收货人姓名", required = true, unique = true)
    @ExcelExport(value = "收货人姓名", example = "王麻子")
    private String receiverName;

    /** 联合唯一性验证 **/
    @ExcelImport(value = "收货人电话", maxLength = 11, required = true, unique = true)
    @ExcelExport(value = "收货人电话", example = "15827596636")
    private String receiverPhone;

    @ExcelImport("收货人邮编")
    @ExcelExport(value = "收货人邮编", example = "010000")
    private String receiverPostCode;

    @ExcelImport("省份/直辖市")
    @ExcelExport(value = "省份/直辖市", example = "内蒙古自治区")
    private String receiverProvince;

    @ExcelImport("城市")
    @ExcelExport(value = "城市", example = "呼和浩特市")
    private String receiverCity;

    @ExcelImport("区")
    @ExcelExport(value = "区", example = "新城区")
    private String receiverRegion;

    @ExcelImport("详细地址")
    @ExcelExport(value = "详细地址", example = "XXX街道XXX小区XXX户")
    private String receiverDetailAddress;

    @ExcelImport(value = "订单备注", maxLength = 500)
    @ExcelExport(value = "订单备注", example = "易碎物品")
    private String note;

    @ExcelImport(value = "确认收货状态", kv = "0-未确认;1-已确认")
    @ExcelExport(value = "确认收货状态", kv = "0-未确认;1-已确认", example = "1")
    private Integer confirmStatus;

    @ExcelImport(value = "删除状态", kv = "0-未删除;1-已删除", required = true)
    @ExcelExport(value = "删除状态", kv = "0-未删除;1-已删除", example = "0")
    private Integer deleteStatus;

    @ExcelImport("下单时使用的积分")
    @ExcelExport(value = "下单时使用的积分", example = "200")
    private Integer useIntegration;

    @ExcelImport("支付时间")
    @ExcelExport(value = "支付时间", example = "2018-10-11 14:04:19")
    private Date paymentTime;

    @ExcelImport("发货时间")
    @ExcelExport(value = "发货时间", example = "2018-10-13 13:43:41")
    private Date deliveryTime;

    @ExcelImport("确认收货时间")
    @ExcelExport(value = "确认收货时间", example = "2018-10-18 13:43:41")
    private Date receiveTime;

    @ExcelImport("评价时间")
    @ExcelExport(value = "评价时间", example = "2018-10-19 13:43:41")
    private Date commentTime;

    @ExcelImport("修改时间")
    @ExcelExport(value = "修改时间", example = "2018-10-11 14:03:19")
    private Date modifyTime;
}