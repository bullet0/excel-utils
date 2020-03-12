package com.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.convert.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @program: com.entity.Obj
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-11 16:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Obj {
    private int a;
    @NumberFormat("#.##%")
    private double b;

//    @DateTimeFormat("yyyy/MM/dd")
    @ExcelProperty(value = {"111","222","333"},index = 0,converter = LocalDateConverter.class)
    private LocalDate c;
}
