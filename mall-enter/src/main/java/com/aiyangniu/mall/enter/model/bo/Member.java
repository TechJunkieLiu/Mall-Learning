package com.aiyangniu.mall.enter.model.bo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 会员导出
 *
 * @author lzq
 * @date 2023/06/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Member {

    @ExcelProperty("ID")
    @ColumnWidth(10)
    private Long id;

    @ExcelProperty("用户名")
    @ColumnWidth(20)
    private String username;

    @ExcelIgnore
    private String password;

    @ExcelProperty("昵称")
    @ColumnWidth(20)
    private String nickname;

    @ExcelProperty("出生日期")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;

    @ExcelProperty("手机号")
    @ColumnWidth(20)
    private String phone;

    @ExcelIgnore
    private String icon;

    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    @ColumnWidth(10)
    private Integer gender;
}
