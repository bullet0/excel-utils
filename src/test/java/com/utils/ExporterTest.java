package com.utils;

import com.alibaba.excel.ExcelWriter;
import com.entity.Obj;
import com.entity.SecondObj;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

public class ExporterTest {
    Exporter exporter;
    @Before
    public void setUp(){
        exporter = new Exporter();
    }
    @Test
    public void simpleWrite() {
        List<Obj> data = new ArrayList<>();
        data.add(new Obj(1,1.1, LocalDate.of(2012,1,15)));
        data.add(new Obj(1,1.1, LocalDate.of(2012,1,15)));
        data.add(new Obj(1,1.1, LocalDate.of(2012,1,15)));
        data.add(new Obj(1,1.1, LocalDate.of(2012,1,15)));
        System.out.println(data);

        exporter.fileWrite(data, Obj.class,"./out/","名字.xlsx","shut");
        exporter.fileWrite(data, Obj.class,"./out/","名字2.xlsx","mumu");
    }

    @Test
    public void multipleSheetWrite() {
        List<String> sheetNames = new ArrayList<String>(){{
            add("shut1");
            add("shut2");
            add("shut3");
        }};
        Class<?> head = Obj.class;
        List<List<Object>> datas = new ArrayList<List<Object>>(){{

           add(new ArrayList<Object>(){{
                add(new Obj(1,1.1,LocalDate.of(2015,1,1)));
                add(new Obj(2,1.1,LocalDate.of(2015,1,1)));
                add(new Obj(3,1.1,LocalDate.of(2015,1,1)));
           }});
           add(new ArrayList<Object>(){{
                add(new Obj(1,1.1,LocalDate.of(2015,1,1)));
                add(new Obj(2,1.1,LocalDate.of(2015,1,1)));
                add(new Obj(3,1.1,LocalDate.of(2015,1,1)));
           }});
           add(new ArrayList<Object>(){{
                add(new Obj(1,1.1,LocalDate.of(2015,1,1)));
                add(new Obj(2,1.1,LocalDate.of(2015,1,1)));
                add(new Obj(3,1.1,LocalDate.of(2015,1,1)));
           }});
        }};
        String dest = "./out";
        String fileName = "aaa.xlsx";
        exporter.multipleSheetWrite(sheetNames,head,datas,dest,fileName);
    }

    @Test
    public void flexibleWrite() {
        List<Obj> data = new ArrayList<>();
        data.add(new Obj(1,1.1, LocalDate.of(2012,1,15)));
        data.add(new Obj(1,1.1, LocalDate.of(2012,1,15)));
        data.add(new Obj(1,1.1, LocalDate.of(2012,1,15)));
        data.add(new Obj(1,1.1, LocalDate.of(2012,1,15)));
        List<SecondObj> data2 = new ArrayList<>();
        data2.add(new SecondObj(1,1.5, LocalDate.of(2012,1,15)));
        data2.add(new SecondObj(1,1.5, LocalDate.of(2012,1,15)));
        data2.add(new SecondObj(1,1.5, LocalDate.of(2012,1,15)));
        data2.add(new SecondObj(1,1.5, LocalDate.of(2012,1,15)));
        ExcelWriter excelWriter = exporter.getExcelWriter("./out", "bb.xlsx");
        exporter.flexibleWrite(excelWriter,data,Obj.class,0,"1页",null,null)
                .flexibleWrite(excelWriter,data2,SecondObj.class,0,"1页",Arrays.asList("a1"),Arrays.asList("c1"))
                .finish(excelWriter);
    }
}
