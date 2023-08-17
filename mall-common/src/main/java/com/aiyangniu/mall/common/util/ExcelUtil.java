//package com.aiyangniu.mall.common.util;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.metadata.Head;
//import com.alibaba.excel.write.metadata.WriteSheet;
//import com.alibaba.excel.write.metadata.style.WriteCellStyle;
//import com.alibaba.excel.write.metadata.style.WriteFont;
//import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
//import com.alibaba.excel.write.style.column.AbstractHeadColumnWidthStyleStrategy;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.codec.binary.StringUtils;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.util.CollectionUtils;
//
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Field;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Excel工具类
// * 报表导出工具类（多sheet）
// *
// * @author lzq
// * @date 2023/06/12
// */
//public class ExcelUtil {
//
//    /**
//     * 基于POI的数据导出
//     *
//     * @param fileName 导出excel名称
//     * @param data 导出的JSON数据
//     * @param clazz 导出数据的实体
//     * @param response 响应
//     */
//    public static void exportExcel(String fileName, String data, Class<?> clazz, HttpServletResponse response) throws Exception {
//        try {
//            // 创建表头
//            // 创建工作薄
//            Workbook workbook = new XSSFWorkbook();
//            Sheet sheet = workbook.createSheet();
//            // 创建表头行
//            Row rowHeader = sheet.createRow(0);
//            if (clazz == null) {
//                throw new RuntimeException("Class对象不能为空!");
//            }
//            Field[] declaredFields = clazz.getDeclaredFields();
//            List<String> headerList = new ArrayList<>();
//            if (declaredFields.length == 0) {
//                return;
//            }
//            for (int i = 0; i < declaredFields.length; i++) {
//                Cell cell = rowHeader.createCell(i, CellType.STRING);
//                String headerName = String.valueOf(declaredFields[i].getName());
//                cell.setCellValue(headerName);
//                headerList.add(i, headerName);
//            }
//            // 填充数据
//            List<?> objects = JSONObject.parseArray(data, clazz);
//            Object obj = clazz.newInstance();
//            if (!CollectionUtils.isEmpty(objects)) {
//                for (int o = 0; o < objects.size(); o++) {
//                    Row rowData = sheet.createRow(o + 1);
//                    for (int i = 0; i < headerList.size(); i++) {
//                        Cell cell = rowData.createCell(i);
//                        Field nameField = clazz.getDeclaredField(headerList.get(i));
//                        nameField.setAccessible(true);
//                        String value = String.valueOf(nameField.get(objects.get(o)));
//                        cell.setCellValue(value);
//                    }
//                }
//            }
//            response.setContentType("application/vnd.ms-excel");
//            String resultFileName = URLEncoder.encode(fileName, "UTF-8");
//            response.setHeader("Content-disposition", "attachment;filename=" + resultFileName + ";" + "filename*=utf-8''" + resultFileName);
//            workbook.write(response.getOutputStream());
//            workbook.close();
//            response.flushBuffer();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 基于POI的数据导入
//     * 仅限八大基础数据类型和String类型，其他类型需要扩展
//     * Workbook workbook = WorkbookFactory.create(file.getInputStream());
//     *
//     * @param workbook 工作簿
//     * @param clazz 实体类
//     */
//    public static <T> List<T> importExcel(Workbook workbook, Class<?> clazz){
//        List<T> dataList = new ArrayList<>();
//        try {
//            Sheet sheet = workbook.getSheetAt(0);
//            int i = 0;
//            T o = null;
//            for (Row rows : sheet) {
//                Row row = sheet.getRow(i + 1);
//                if(row != null){
//                    o = (T) clazz.newInstance();
//                    Field[] declaredFields = clazz.getDeclaredFields();
//                    for (int j = 0; j < declaredFields.length; j++) {
//                        String name = declaredFields[j].getName();
//                        Field declaredField = o.getClass().getDeclaredField(name);
//                        declaredField.setAccessible(true);
//                        Cell cell = row.getCell(j);
//                        String type = declaredFields[j].getType().getName();
//                        String value = String.valueOf(cell);
//                        if(StringUtils.equals(type, "int") || StringUtils.equals(type, "Integer")){
//                            declaredField.set(o, Integer.parseInt(value));
//                        } else if(StringUtils.equals(type, "java.lang.String") || StringUtils.equals(type, "char") || StringUtils.equals(type, "Character") ||
//                                StringUtils.equals(type, "byte") || StringUtils.equals(type, "Byte")){
//                            declaredField.set(o, value);
//                        } else if(StringUtils.equals(type, "boolean") || StringUtils.equals(type, "Boolean")){
//                            declaredField.set(o, Boolean.valueOf(value));
//                        } else if(StringUtils.equals(type, "double") || StringUtils.equals(type, "Double")){
//                            declaredField.set(o, Double.valueOf(value));
//                        } else if (StringUtils.equals(type, "long") || StringUtils.equals(type, "Long")) {
//                            declaredField.set(o, Long.valueOf(value));
//                        } else if(StringUtils.equals(type, "short") || StringUtils.equals(type, "Short")){
//                            declaredField.set(o, Short.valueOf(value));
//                        } else if(StringUtils.equals(type, "float") || StringUtils.equals(type, "Float")){
//                            declaredField.set(o, Float.valueOf(value));
//                        }
//                    }
//                }
//                dataList.add(o);
//            }
//            workbook.close();
//            return dataList;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return dataList;
//    }
//
//    /**
//     * 基于EasyExcel的数据导出
//     *
//     * @param fileName 导出excel名称
//     * @param map 导出的Map数据
//     * @param excelType excel格式（.xls .xlsx）
//     * @param response 响应
//     */
//    public static <T> void downloadExcelFile(String fileName, Map<String, List<Object>> map, String excelType, HttpServletResponse response) {
//        if (fileName == null || "".equals(fileName.trim())) {
//            throw new IllegalArgumentException("fileName should not be empty!");
//        }
//        if (excelType == null) {
//            throw new IllegalArgumentException("excelType should not be null!");
//        }
//
//        fileName += excelType;
//        try {
//            configResponse(response, fileName);
//            // 将数据写入sheet页中
//            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
//            WriteSheet firstSheet = EasyExcel
//                    .writerSheet(1, "报表1")
//                    // 导出报表实体（仅作为Excel表头展示用）
//                    .head(Object.class)
//                    .registerWriteHandler(getStyleStrategy())
//                    .registerWriteHandler(new ExcelStyle())
//                    .build();
//            WriteSheet secondSheet = EasyExcel
//                    .writerSheet(2,"报表2")
//                    // 导出报表实体（仅作为Excel表头展示用）
//                    .head(Object.class)
//                    .registerWriteHandler(getStyleStrategy())
//                    .registerWriteHandler(new ExcelStyle())
//                    .build();
//
//            excelWriter.write(map.get("key1"), firstSheet);
//            excelWriter.write(map.get("key2"), secondSheet);
//            excelWriter.finish();
//            response.flushBuffer();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 简单设置样式
//     */
//    private static HorizontalCellStyleStrategy getStyleStrategy(){
//        // 内容样式策略
//        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
//        // 设置自动换行
//        contentWriteCellStyle.setWrapped(false);
//        // 字体策略
//        WriteFont contentWriteFont = new WriteFont();
//        // 字体大小
//        contentWriteFont.setFontHeightInPoints((short) 12);
//        contentWriteCellStyle.setWriteFont(contentWriteFont);
//        // 头策略使用默认
//        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
//        headWriteCellStyle.setWrapped(true);
//        WriteFont headWriteFont = new WriteFont();
//        headWriteFont.setBold(false);
//        // 字体大小
//        headWriteFont.setFontHeightInPoints((short) 14);
//        headWriteCellStyle.setWriteFont(headWriteFont);
//        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
//    }
//
//    private static void configResponse(HttpServletResponse response, String fileName) {
//        try {
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//            response.setContentType("*");
//            response.setCharacterEncoding("UTF-8");
//            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//class ExcelStyle extends AbstractHeadColumnWidthStyleStrategy {
//    @Override
//    protected Integer columnWidth(Head head, Integer integer) {
//        int defaultWidth = 6;
//        float multiValue = 3.8f;
//        List<String> headNameList = head.getHeadNameList();
//        if(CollectionUtils.isEmpty(headNameList)){
//            return Math.round(defaultWidth * multiValue);
//        }
//        return Math.round(headNameList.get(headNameList.size()-1).length() * multiValue);
//    }
//}