package com.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.core.AbstractExporter;

import java.io.*;
import java.util.Collection;
import java.util.List;

/**
 * @program: com.utils.Exportor
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-11 15:02
 */
public class Exporter extends AbstractExporter {
    /**
     * @author Mr.BULLET
     * 导出简单数据
     */
    public void fileWrite(List<?> data,Class<?> head,String dest,String fileName) {
        File file = new File(dest);
        if(!file.exists()){
            file.mkdirs();
        }
        fileName = dest + File.separator + fileName;
        EasyExcel.write(fileName,head)
                .sheet()
                .doWrite(data);
    }
    public void fileWrite(List<?> data,Class<?> head,String dest,String fileName,
                            String sheetName) {
        File file = new File(dest);
        if(!file.exists()){
            file.mkdirs();
        }
        fileName = dest + File.separator + fileName;
        EasyExcel.write(fileName,head)
                .sheet(sheetName)
                .doWrite(data);
    }


    /**
     * @author Mr.BULLET
     * 拆开方便多次调用
     *  配合{@link AbstractExporter#getExcelWriter(String, String)}
     *  和 {@link AbstractExporter#finish(ExcelWriter)} 使用
     */
    public Exporter flexibleWrite(ExcelWriter excelWriter,List<?> data,
                              Class<?> head,int sheetNumber,String sheetName,
                              Collection<String> excludeColumnFiledNames,
                              Collection<String> includeColumnFiledNames){
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetNumber, sheetName)
                .excludeColumnFiledNames(excludeColumnFiledNames)
                .includeColumnFiledNames(includeColumnFiledNames)
                .head(head).build();
        excelWriter.write(data, writeSheet);
        return this;
    }

    /**
     * @author Mr.BULLET
     * 多条数据分别写入多个sheet:比如10条数据分别写入5个sheet
     * @param sheetNames
     * @param head
     * @param data
     */
    public void multipleSheetWrite(List<String> sheetNames, Class<?> head, List<List<Object>> data, String dest, String fileName){
        // 这里 指定文件
        ExcelWriter excelWriter = this.getExcelWriter(dest,fileName);
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < sheetNames.size(); i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
            WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetNames.get(i)).head(head).build();
            excelWriter.write(data.get(i), writeSheet);
        }
        /// 千万别忘记finish 会帮忙关闭流
        this.finish(excelWriter);
    }

    public void outputStreamWrite(OutputStream outputStream,List<Object> data,Class<?> head,String sheetName){
        EasyExcel.write(outputStream, head).sheet(sheetName).doWrite(data);
    }
    /**
     * @author Mr.BULLET
     * 配合{@link AbstractExporter#getOutputStreamWriter(OutputStream)}
     * 和 {@link AbstractExporter#finish(ExcelWriter)} 使用
     * web端导出时使用
     */
    public void outputStreamFlexibleWrite(ExcelWriter excelWriter,List<Object> data,Class<?> head,
                                          int sheetNumber, String sheetName,
                                          Collection<String> excludeColumnFiledNames,
                                          Collection<String> includeColumnFiledNames){
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetNumber, sheetName)
                .excludeColumnFiledNames(excludeColumnFiledNames)
                .includeColumnFiledNames(includeColumnFiledNames)
                .head(head).build();
        excelWriter.write(data, writeSheet);
    }
}
