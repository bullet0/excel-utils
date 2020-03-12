package com.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.convert.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @program: com.entity.SecendObj
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-11 19:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecondObj {
    private int a;
    private double b;
    @ExcelProperty(converter = LocalDateConverter.class)
    private LocalDate c;
}
