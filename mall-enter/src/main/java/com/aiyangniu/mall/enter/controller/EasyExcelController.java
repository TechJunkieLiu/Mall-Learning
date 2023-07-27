package com.aiyangniu.mall.enter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.common.util.LocalJsonUtil;
import com.aiyangniu.mall.enter.annotation.CustomMerge;
import com.aiyangniu.mall.enter.model.bo.Member;
import com.aiyangniu.mall.enter.model.bo.Order;
import com.aiyangniu.mall.enter.model.bo.OrderData;
import com.aiyangniu.mall.enter.model.bo.Product;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyExcel导入导出测试
 *
 * @author lzq
 * @date 2023/06/12
 */
@Api(tags = "EasyExcelController")
@Tag(name = "EasyExcelController", description = "EasyExcel导入导出测试")
@RestController
@RequestMapping("/easyExcel")
public class EasyExcelController {

    @SneakyThrows(IOException.class)
    @ApiOperation(value = "导出会员列表Excel")
    @GetMapping(value = "/exportMemberList")
    public void exportMemberList(HttpServletResponse response) {
        setExcelRespProp(response, "会员列表");
        List<Member> memberList = LocalJsonUtil.getListFromJson("json/members.json", Member.class);
        EasyExcel.write(response.getOutputStream())
                .head(Member.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("会员列表")
                .doWrite(memberList);
    }

    @SneakyThrows
    @ApiOperation(value = "导出订单列表Excel")
    @GetMapping(value = "/exportOrderList")
    public void exportOrderList(HttpServletResponse response) {
        List<Order> orderList = getOrderList();
        List<OrderData> orderDataList = convert(orderList);
        setExcelRespProp(response, "订单列表");
        EasyExcel.write(response.getOutputStream())
                .head(OrderData.class)
                // 自定义单元格合并策略
                .registerWriteHandler(new CustomMergeStrategy(OrderData.class))
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("订单列表")
                .doWrite(orderDataList);
    }

    @SneakyThrows
    @ApiOperation("从Excel导入会员列表")
    @PostMapping(value = "/importMemberList")
    public CommonResult importMemberList(@RequestPart("file") MultipartFile file) {
        List<Member> memberList = EasyExcel.read(file.getInputStream())
                .head(Member.class)
                .sheet()
                .doReadSync();
        return CommonResult.success(memberList);
    }

    private List<Order> getOrderList() {
        List<Order> orderList = LocalJsonUtil.getListFromJson("json/orders.json", Order.class);
        List<Product> productList = LocalJsonUtil.getListFromJson("json/products.json", Product.class);
        List<Member> memberList = LocalJsonUtil.getListFromJson("json/members.json", Member.class);
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            order.setMember(memberList.get(i));
            order.setProductList(productList);
        }
        return orderList;
    }

    private List<OrderData> convert(List<Order> orderList) {
        List<OrderData> result = new ArrayList<>();
        for (Order order : orderList) {
            List<Product> productList = order.getProductList();
            for (Product product : productList) {
                OrderData orderData = new OrderData();
                BeanUtil.copyProperties(product,orderData);
                BeanUtil.copyProperties(order,orderData);
                result.add(orderData);
            }
        }
        return result;
    }


    /**
     * 设置excel下载响应头属性
     */
    private void setExcelRespProp(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }
}

/**
 * 自定义单元格合并策略
 */
class CustomMergeStrategy implements RowWriteHandler {

    /**
     * 主键下标
     */
    private Integer pkIndex;

    /**
     * 需要合并的列的下标集合
     */
    private List<Integer> needMergeColumnIndex = new ArrayList<>();

    /**
     * DTO数据类型
     */
    private Class<?> elementType;

    public CustomMergeStrategy(Class<?> elementType) {
        this.elementType = elementType;
    }

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer relativeRowIndex, Boolean isHead) {
        // 如果是标题，则直接返回
        if (isHead) {
            return;
        }
        // 获取当前sheet
        Sheet sheet = writeSheetHolder.getSheet();
        // 获取标题行
        Row titleRow = sheet.getRow(0);

        if (null == pkIndex) {
            this.lazyInit(writeSheetHolder);
        }

        // 判断是否需要和上一行进行合并
        // 不能和标题合并，只能数据行之间合并
        if (row.getRowNum() <= 1) {
            return;
        }
        // 获取上一行数据
        Row lastRow = sheet.getRow(row.getRowNum() - 1);
        // 将本行和上一行是同一类型的数据(通过主键字段进行判断)，则需要合并
        if (lastRow.getCell(pkIndex).getStringCellValue().equalsIgnoreCase(row.getCell(pkIndex).getStringCellValue())) {
            for (Integer needMerIndex : needMergeColumnIndex) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(row.getRowNum() - 1, row.getRowNum(),
                        needMerIndex, needMerIndex);
                sheet.addMergedRegionUnsafe(cellRangeAddress);
            }
        }
    }

    /**
     * 初始化主键下标和需要合并字段的下标
     */
    private void lazyInit(WriteSheetHolder writeSheetHolder) {

        // 获取当前sheet
        Sheet sheet = writeSheetHolder.getSheet();
        // 获取标题行
        Row titleRow = sheet.getRow(0);
        // 获取DTO的类型
        Class<?> eleType = this.elementType;

        // 获取DTO所有的属性
        Field[] fields = eleType.getDeclaredFields();

        // 遍历所有的字段，因为是基于DTO的字段来构建excel，所以字段数 >= excel的列数
        for (Field theField : fields) {
            // 获取@ExcelProperty注解，用于获取该字段对应在excel中的列的下标
            ExcelProperty easyExcelAnno = theField.getAnnotation(ExcelProperty.class);
            // 为空,则表示该字段不需要导入到excel,直接处理下一个字段
            if (null == easyExcelAnno) {
                continue;
            }
            // 获取自定义的注解，用于合并单元格
            CustomMerge customMerge = theField.getAnnotation(CustomMerge.class);

            // 没有@CustomMerge注解的默认不合并
            if (null == customMerge) {
                continue;
            }

            for (int index = 0; index < fields.length; index++) {
                Cell theCell = titleRow.getCell(index);
                // 当配置为不需要导出时，返回的为null，这里作一下判断，防止NPE
                if (null == theCell) {
                    continue;
                }
                // 将字段和excel的表头匹配上
                if (easyExcelAnno.value()[0].equalsIgnoreCase(theCell.getStringCellValue())) {
                    if (customMerge.isPk()) {
                        pkIndex = index;
                    }

                    if (customMerge.needMerge()) {
                        needMergeColumnIndex.add(index);
                    }
                }
            }
        }

        // 没有指定主键，则异常
        if (null == this.pkIndex) {
            throw new IllegalStateException("使用@CustomMerge注解必须指定主键");
        }
    }
}



