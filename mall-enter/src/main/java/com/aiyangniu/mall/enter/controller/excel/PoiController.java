package com.aiyangniu.mall.enter.controller.excel;

import com.aiyangniu.mall.common.util.ExcelUtils.poi.ExcelUtil;
import com.aiyangniu.mall.enter.mapper.OmsOrderPoiExcelMapper;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.*;

/**
 * EasyPoi导入导出测试
 * 问题一：导出多Sheet页
 * 问题二：导出文件名中文乱码
 *
 * @author lzq
 * @date 2023/08/17
 */
@Api(value = "PoiController", tags = "Poi 导入导出测试")
@RestController
@RequestMapping("/poi")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PoiController {

    private final OmsOrderPoiExcelMapper omsOrderPoiExcelMapper;

    /**
     * 导入解析为JSON
     */
    @PostMapping("/importForJson")
    public JSONArray importForJson(@RequestPart("file") MultipartFile file) throws Exception {
        JSONArray array = ExcelUtil.readMultipartFile(file);
        System.out.println("导入数据为:" + array);
        return array;
    }

    /**
     * 导入解析为对象
     */
    @PostMapping("/importForObj")
    public void importForObj(@RequestPart("file") MultipartFile file) throws Exception {
        List<OmsOrderForPoi> orderList = ExcelUtil.readMultipartFile(file, OmsOrderForPoi.class);
        for (OmsOrderForPoi order : orderList) {
            System.out.println(order.toString());
        }
    }

    /**
     * 导入多Sheet页
     */
    @PostMapping("/importSheetsForObj")
    public void importSheetsForObj(@RequestPart("file") MultipartFile file) throws Exception {
        Map<String, JSONArray> map = ExcelUtil.readFileManySheet(file);
        map.forEach((key, value) -> {
            System.out.println("Sheet名称：" + key);
            System.out.println("Sheet数据：" + value);
            System.out.println("----------------------");
        });
    }

    /**
     * 动态导出（导出图片）
     */
    @GetMapping("/exportForPhoto")
    public void exportForPhoto(HttpServletResponse response) throws Exception {
        // 表头数据
        List<Object> head = Arrays.asList("姓名", "年龄", "性别", "头像");
        // 用户1数据
        List<Object> user1 = new ArrayList<>();
        user1.add("诸葛亮");
        user1.add(60);
        user1.add("男");
        user1.add(new URL("https://profile-avatar.csdnimg.cn/default.jpg!1"));
        // 用户2数据
        List<Object> user2 = new ArrayList<>();
        user2.add("大乔");
        user2.add(28);
        user2.add("女");
        user2.add(new URL("https://img-home.csdnimg.cn/images/20201124032511.png"));
        // 将数据汇总
        List<List<Object>> sheetDataList = new ArrayList<>();
        sheetDataList.add(head);
        sheetDataList.add(user1);
        sheetDataList.add(user2);
        // 导出数据
        ExcelUtil.export(response, "用户表", sheetDataList);
    }

    /**
     * 动态导出（导出下拉列表）
     */
    @GetMapping("/exportForList")
    public void exportForList(HttpServletResponse response) {
        // 表头数据
        List<Object> head = Arrays.asList("姓名", "年龄", "性别", "城市");
        // 用户1数据
        List<Object> user1 = new ArrayList<>();
        user1.add("诸葛亮");
        user1.add(60);
        user1.add("男");
        user1.add("包头");
        // 用户2数据
        List<Object> user2 = new ArrayList<>();
        user2.add("大乔");
        user2.add(28);
        user2.add("女");
        user2.add("呼和浩特");
        // 将数据汇总
        List<List<Object>> sheetDataList = new ArrayList<>();
        sheetDataList.add(head);
        sheetDataList.add(user1);
        sheetDataList.add(user2);
        // 设置下拉列表（键为第几列，值为下拉数据，从0开始）
        Map<Integer, List<String>> selectMap = new HashMap<>(1);
        selectMap.put(2, Arrays.asList("男", "女"));
        selectMap.put(3, Arrays.asList("鄂尔多斯", "赤峰", "包头", "呼和浩特"));
        // 导出数据
        ExcelUtil.export(response, "用户表", sheetDataList, selectMap);
    }

    /**
     * 动态导出（横向、纵向合并）
     */
    @GetMapping("/exportForWHMerge")
    public void exportForWHMerge(HttpServletResponse response) {
        // 表头数据
        List<Object> head = Arrays.asList("归属地", "姓名", "年龄", "性别", "地址", ExcelUtil.COLUMN_MERGE);
        // 用户1数据
        List<Object> user1 = new ArrayList<>();
        user1.add("蜀国");
        user1.add("诸葛亮");
        user1.add(60);
        user1.add("男");
        user1.add("包头");
        user1.add("阿尔丁大街");
        // 用户2数据
        List<Object> user2 = new ArrayList<>();
        user2.add("吴国");
        user2.add("大乔");
        user2.add(28);
        user2.add("女");
        user2.add("呼和浩特");
        user2.add("成吉思汗大街");
        // 用户2数据
        List<Object> user3 = new ArrayList<>();
        user3.add(ExcelUtil.ROW_MERGE);
        user3.add("小乔");
        user3.add(18);
        user3.add("女");
        user3.add("鄂尔多斯");
        user3.add("中央大街");
        // 将数据汇总
        List<List<Object>> sheetDataList = new ArrayList<>();
        sheetDataList.add(head);
        sheetDataList.add(user1);
        sheetDataList.add(user2);
        sheetDataList.add(user3);
        // 导出数据
        ExcelUtil.export(response, "用户表", sheetDataList);
    }

    /**
     * 导出
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        List<OmsOrderForPoi> orderList = omsOrderPoiExcelMapper.selectList(new LambdaQueryWrapper<>());
        ExcelUtil.export(response, "订单表", orderList, OmsOrderForPoi.class);
        // 导出模板（测试实体标签 example）
//        ExcelUtil.exportTemplate(response, "订单表", OmsOrder.class, true);
    }

    /**
     * 导出（多Sheet页）
     */
    @GetMapping("/exportManySheet")
    public void exportManySheet(HttpServletResponse response) {
        // 第1个Sheet页
        List<List<Object>> sheet1 = new ArrayList<>();
        List<Object> sheet1Head = new ArrayList<>();
        sheet1Head.add("姓名");
        sheet1Head.add("数学");
        sheet1Head.add("英语");
        sheet1.add(sheet1Head);
        List<Object> row1 = new ArrayList<>();
        row1.add("Jack");
        row1.add(85);
        row1.add(100);
        sheet1.add(row1);
        List<Object> row2 = new ArrayList<>();
        row2.add("Marry");
        row2.add(85);
        row2.add(100);
        sheet1.add(row2);
        // 第2个Sheet页
        List<List<Object>> sheet2 = new ArrayList<>();
        List<Object> sheet2Head = new ArrayList<>();
        sheet2Head.add("姓名");
        sheet2Head.add("音乐");
        sheet2Head.add("美术");
        sheet2.add(sheet2Head);
        List<Object> row01 = new ArrayList<>();
        row01.add("Jack");
        row01.add(77);
        row01.add(66);
        sheet2.add(row01);
        List<Object> row02 = new ArrayList<>();
        row02.add("Marry");
        row02.add(99);
        row02.add(88);
        sheet2.add(row02);
        // 将两个Sheet页添加到集合中
        Map<String, List<List<Object>>> sheets = new LinkedHashMap<>();
        sheets.put("文化课", sheet1);
        sheets.put("艺术课", sheet2);
        // 导出数据
        ExcelUtil.exportManySheet(response, "学生成绩表", sheets);
    }
}
