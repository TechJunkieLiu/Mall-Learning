package com.aiyangniu.mall.enter.controller.excel;

import com.aiyangniu.mall.common.util.ExcelUtils.easypoi.ExcelUtil;
import com.aiyangniu.mall.enter.mapper.OmsOrderEasyPoiMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * EasyPoi导入导出测试
 *
 * @author lzq
 * @date 2023/08/22
 */
@Api(value = "EasyPoiController", tags = "EasyPoi 导入导出测试")
@RestController
@RequestMapping("/easyPoi")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EasyPoiController {

    private final OmsOrderEasyPoiMapper omsOrderEasyPoiMapper;

    @PostMapping(value = "/import")
    public void importExcel(@RequestPart("file") MultipartFile file) throws IOException {
        List<OmsOrderForEasyPoi> list = ExcelUtil.importExcel(file, 1, 1, OmsOrderForEasyPoi.class);
        System.out.println(list);
    }

    @PostMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<OmsOrderForEasyPoi> list = omsOrderEasyPoiMapper.selectList(new LambdaQueryWrapper<>());
        ExcelUtil.exportExcel(list, "title", "sheetName", OmsOrderForEasyPoi.class, "fileName", true, response);
    }
}
