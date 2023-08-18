package com.aiyangniu.mall.enter.controller.excel;

import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.common.util.ExcelUtils.easyexcel.ExcelUtil;
import com.aiyangniu.mall.enter.mapper.OmsOrderEasyExcelMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * EasyExcel导入导出测试
 *
 * @author lzq
 * @date 2023/08/18
 */
@Api(value = "EasyExcelController", tags = "EasyExcel 导入导出测试")
@RestController
@RequestMapping("/easyExcel")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EasyExcelController {

    private final OmsOrderEasyExcelMapper omsOrderEasyExcelMapper;

    /**
     * Excel模板下载
     */
    @GetMapping(value = "/template")
    public CommonResult<String> template(HttpServletResponse response){
        String fileName = "导入模板下载" + System.currentTimeMillis();
        try {
            ExcelUtil.export(fileName, null, OmsOrderForEasyExcel.class, response);
        } catch (Exception e) {
            return CommonResult.failed("模板下载失败" + e.getMessage());
        }
        return CommonResult.success("模板下载成功！");
    }

    /**
     * Excel批量导入数据
     */
    @PostMapping(value = "/import")
    public CommonResult<String> importEvents(@RequestPart("file") MultipartFile file){
        try {
            List<?> list = ExcelUtil.importExcel(file, OmsOrderForEasyExcel.class);
            System.out.println(list);
            return CommonResult.success("数据导入完成");
        } catch (Exception e) {
            return CommonResult.failed("数据导入失败！" + e.getMessage());
        }
    }

    /**
     * Excel数据导出
     */
    @GetMapping(value = "/export")
    public CommonResult<String> export(HttpServletResponse response){
        List<OmsOrderForEasyExcel> orderList = omsOrderEasyExcelMapper.selectList(new LambdaQueryWrapper<>());
        String fileName = "数据导出" + System.currentTimeMillis();
        try {
            ExcelUtil.export(fileName, orderList, OmsOrderForEasyExcel.class, response);
        } catch (Exception e) {
            return CommonResult.failed("数据导出成功" + e.getMessage());
        }
        return CommonResult.success("数据导出失败！");
    }
}
