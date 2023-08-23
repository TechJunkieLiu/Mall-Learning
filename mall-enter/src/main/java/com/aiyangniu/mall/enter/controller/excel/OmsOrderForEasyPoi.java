package com.aiyangniu.mall.enter.controller.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单导入导出实体
 * 导入的excel内容必须包含表头和标题，否则读取不到内容
 *
 * @author lzq
 * @date 2023/08/22
 */
@Data
@TableName(value = "oms_order")
public class OmsOrderForEasyPoi implements Serializable {

    private static final long serialVersionUID = -6075777425108538072L;

    @TableId(value = "id")
    @Excel(name = "ID", isImportField = "true", width = 10)
    private Long id;

    @TableField(value = "member_id")
    @Excel(name = "会员ID", orderNum = "1", width = 20)
    private Long memberId;

    @TableField(value = "coupon_id")
    @Excel(name = "优惠券ID", orderNum = "2", width = 30)
    private Long couponId;

    @TableField(value = "order_sn")
    @Excel(name = "订单编号", orderNum = "3", width = 40)
    private String orderSn;

    @TableField(value = "create_time")
    @Excel(name = "提交时间", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss", databaseFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "member_username")
    @Excel(name = "用户帐号")
    private String memberUsername;

    @TableField(value = "total_amount")
    @Excel(name = "订单总金额")
    private BigDecimal totalAmount;

    @TableField(value = "pay_amount")
    @Excel(name = "应付金额")
    private BigDecimal payAmount;

    @TableField(value = "freight_amount")
    @Excel(name = "运费金额")
    private BigDecimal freightAmount;

    @TableField(value = "promotion_amount")
    @Excel(name = "促销优化金额")
    private BigDecimal promotionAmount;

    @TableField(value = "integration_amount")
    @Excel(name = "积分抵扣金额")
    private BigDecimal integrationAmount;

    @TableField(value = "coupon_amount")
    @Excel(name = "优惠券抵扣金额")
    private BigDecimal couponAmount;

    @TableField(value = "discount_amount")
    @Excel(name = "管理员后台调整订单使用的折扣金额")
    private BigDecimal discountAmount;

    @TableField(value = "pay_type")
    @Excel(name = "支付方式", replace = {"未支付_0", "支付宝_1", "微信_2"})
    private Integer payType;

    @TableField(value = "source_type")
    @Excel(name = "订单来源", replace = {"PC订单_0", "app订单_1"})
    private Integer sourceType;

    @TableField(value = "status")
    @Excel(name = "订单状态", replace = {"待付款_0", "待发货_1", "已发货_2", "已完成_3", "已关闭_4", "无效订单_5"})
    private Integer status;

    @TableField(value = "order_type")
    @Excel(name = "订单类型", replace = {"正常订单_0", "秒杀订单_1"})
    private Integer orderType;

    @TableField(value = "delivery_company")
    @Excel(name = "物流公司")
    private String deliveryCompany;

    @TableField(value = "delivery_sn")
    @Excel(name = "物流单号")
    private String deliverySn;

    @TableField(value = "auto_confirm_day")
    @Excel(name = "自动确认时间（天）")
    private Integer autoConfirmDay;

    @TableField(value = "integration")
    @Excel(name = "可以获得的积分")
    private Integer integration;

    @TableField(value = "growth")
    @Excel(name = "可以活动的成长值")
    private Integer growth;

    @TableField(value = "promotion_info")
    @Excel(name = "活动信息")
    private String promotionInfo;

    @TableField(value = "bill_type")
    @Excel(name = "发票类型", replace = {"不开发票_0", "电子发票_1", "纸质发票_2"})
    private Integer billType;

    @TableField(value = "bill_header")
    @Excel(name = "发票抬头")
    private String billHeader;

    @TableField(value = "bill_content")
    @Excel(name = "发票内容")
    private String billContent;

    @TableField(value = "bill_receiver_phone")
    @Excel(name = "收票人电话")
    private String billReceiverPhone;

    @TableField(value = "bill_receiver_email")
    @Excel(name = "收票人邮箱")
    private String billReceiverEmail;

    @TableField(value = "receiver_name")
    @Excel(name = "收货人姓名")
    private String receiverName;

    @TableField(value = "receiver_phone")
    @Excel(name = "收货人电话")
    private String receiverPhone;

    @TableField(value = "receiver_post_code")
    @Excel(name = "收货人邮编")
    private String receiverPostCode;

    @TableField(value = "receiver_province")
    @Excel(name = "省份/直辖市")
    private String receiverProvince;

    @TableField(value = "receiver_city")
    @Excel(name = "城市")
    private String receiverCity;

    @TableField(value = "receiver_region")
    @Excel(name = "区")
    private String receiverRegion;

    @TableField(value = "receiver_detail_address")
    @Excel(name = "详细地址")
    private String receiverDetailAddress;

    /**
     * 如果为图片字段：
     * type：2表示字段类型为图片
     * imageType：1表示从file读取
     */
    @TableField(value = "note")
    @Excel(name = "订单备注", width = 32, height = 32, type = 2)
    private String note;

    @TableField(value = "confirm_status")
    @Excel(name = "确认收货状态", replace = {"未确认_0", "已确认_1"})
    private Integer confirmStatus;

    @TableField(value = "delete_status")
    @Excel(name = "删除状态", replace = {"未删除_0", "已删除_1"})
    private Integer deleteStatus;

    @TableField(value = "use_integration")
    @Excel(name = "下单时使用的积分")
    private Integer useIntegration;

    @TableField(value = "payment_time")
    @Excel(name = "支付时间", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss", databaseFormat = "yyyy-MM-dd HH:mm:ss")
    private Date paymentTime;

    @TableField(value = "delivery_time")
    @Excel(name = "发货时间", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss", databaseFormat = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryTime;

    @TableField(value = "receive_time")
    @Excel(name = "确认收货时间", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss", databaseFormat = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;

    @TableField(value = "comment_time")
    @Excel(name = "评价时间", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss", databaseFormat = "yyyy-MM-dd HH:mm:ss")
    private Date commentTime;

    @TableField(value = "modify_time")
    @Excel(name = "修改时间", importFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss", databaseFormat = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
}
