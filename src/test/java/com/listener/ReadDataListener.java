package com.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.entity.Obj;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: com.listener.ReadDataListener
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-11 21:48
 */
public class ReadDataListener extends AnalysisEventListener<Obj> {
    final int BATCH_COUNT = 100;
    List<Obj> list = new ArrayList<Obj>();
    @Override
    public void invoke(Obj obj, AnalysisContext analysisContext) {
        list.add(obj);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
//            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
//        saveData();
//        LOGGER.info("所有数据解析完成！");
    }
}
