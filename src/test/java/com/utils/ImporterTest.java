package com.utils;

import com.entity.Obj;
import com.listener.ObjDataListener;
import org.junit.Before;
import org.junit.Test;

public class ImporterTest {
    Importer importer;
    @Before
    public void setup(){
        importer = new Importer();
    }
    @Test
    public void fileRead() {
        importer.fileRead(Obj.class,new ObjDataListener(),"./out/","名字.xlsx");
    }
}
