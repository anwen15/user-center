package com.example.usercenter.once;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Userinfo {

    @ExcelProperty("成员编号")
    private String planetCode;

    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("成员昵称")
    private String username;
}
