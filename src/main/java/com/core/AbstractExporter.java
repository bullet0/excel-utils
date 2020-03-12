package com.core;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @program: com.core.AbstractExporter
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-11 18:13
 */
public abstract class AbstractExporter {

    public ExcelWriter getExcelWriter(String dest,String fileName){
        File file = new File(dest);
        if (!file.exists()) {
            file.mkdirs();
        }
        fileName = dest + File.separator + fileName;
        ExcelWriterBuilder write = EasyExcel.write(fileName);
        AbstractCellStyleStrategy strategy = this.getCellStyleStrategy();
        write.registerWriteHandler(strategy);
        return write.build();
    }
    public ExcelWriter getOutputStreamWriter(OutputStream out){
        ExcelWriterBuilder write = EasyExcel.write(out);
        AbstractCellStyleStrategy strategy = this.getCellStyleStrategy();
        write.registerWriteHandler(strategy);
        return write.build();
    }
    /**
     * @author Mr.BULLET
     * 子类继承可修改样式
     */
    protected AbstractCellStyleStrategy getCellStyleStrategy(){
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为白色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)16);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        //边框,不设置不显示
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        // 背景白色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE1.getIndex());

        // 水平对齐方式
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        // 垂直对齐方式
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置 自动换行
        contentWriteCellStyle.setWrapped(true);

        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setBold(false);
        contentWriteFont.setFontHeightInPoints((short)16);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        return  horizontalCellStyleStrategy;
    }
//    protected abstract void customMultipleSheetWrite(ExcelWriter excelWriter, List<String> sheetNames, Class<?> head, List<List<Object>> data);

    public void finish(ExcelWriter excelWriter){
        excelWriter.finish();
    }
}
