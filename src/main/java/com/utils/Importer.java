package com.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.File;
import java.io.InputStream;

/**
 * @program: com.utils.Importor
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-11 15:02
 */
public class Importer {

    public void fileRead(Class<?> head, AnalysisEventListener listener, String dest, String fileName) {
        fileName = dest + File.separator + fileName;
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, head, listener).sheet().doRead();
    }
    /**
     * @author Mr.BULLET
     * web导入时使用
     */
    public void  inputStreamRead(InputStream inputStream,Class<?> head,AnalysisEventListener listener){
        EasyExcel.read(inputStream, head, listener).sheet().doRead();
    }
}
