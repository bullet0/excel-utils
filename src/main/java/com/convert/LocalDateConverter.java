package com.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDate;

/**
 * @program: com.utils.convert.LocalDateConverter
 * @description: 自定义LocalDate转换器
 * @author: Mr.BULLET
 * @create: 2020-03-11 16:27
 */
public class LocalDateConverter implements Converter<LocalDate> {
    /**
     * @author Mr.BULLET
     * 返回要支持的类型类对象
     */
    @Override
    public Class supportJavaTypeKey() {
        return LocalDate.class;
    }
    /**
     * @author Mr.BULLET
     * 返回对应的excel中数据类型
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }
    /**
     * @author Mr.BULLET
     * excle类型转当前类型
     */
    @Override
    public LocalDate convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return LocalDate.parse(cellData.getStringValue());
    }
    /**
     * @author Mr.BULLET
     * 当前类型转excle类型
     */
    @Override
    public CellData convertToExcelData(LocalDate localDate, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(localDate.toString());
    }
}
