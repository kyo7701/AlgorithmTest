package com.github.kyo7701.poi;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReaderListener extends AnalysisEventListener<Map<Integer, String>> {

    private boolean readCompleted;

    private String excelTemplateId;

    private List<Map> allData = new ArrayList<>();

    private List<Map> failedData = new ArrayList<>();

    private ReaderStrategy strategy;

    private Map<Integer, String> headerMap = new HashMap<>();

    public ReaderStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ReaderStrategy strategy) {
        this.strategy = strategy;
    }

    public String getExcelTemplateId() {
        return excelTemplateId;
    }

    public void setExcelTemplateId(String excelTemplateId) {
        this.excelTemplateId = excelTemplateId;
    }

    public boolean isReadCompleted() {
        return readCompleted;
    }

    public void setReadCompleted(boolean readCompleted) {
        this.readCompleted = readCompleted;
    }

    public List<Map> getFailedData() {
        return failedData;
    }

    public void setFailedData(List<Map> failedData) {
        this.failedData = failedData;
    }

    public ExcelReaderListener() {

    }

    public ExcelReaderListener(String excelTemplateId) {
        this.setExcelTemplateId(excelTemplateId);
    }

    public ExcelReaderListener(ReaderStrategy readerStrategy, String excelTemplateId, List<Map> failedData) {
        this.setStrategy(readerStrategy);
        this.setExcelTemplateId(excelTemplateId);
        this.setFailedData(failedData);
    }

    /**
     * 根据Excel模板解析每一行数据
     * 1.解析header
     * 2.封装数据
     * 3.基础校验
     * 4.业务校验
     */
    @Override
    public void invoke(Map<Integer, String> row, AnalysisContext analysisContext) {
        Integer rowIndex = analysisContext.readRowHolder().getRowIndex();
        if (this.strategy.currentRowIsHeaderRegion(rowIndex)) {
            if (this.strategy.currentRowIsMetaData(rowIndex)) {
                headerMap = this.strategy.readHeader(row);
            }
        } else {
            Map<String, String> data = this.strategy.readData(headerMap, row);
            allData.add(data);

        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //可以在这里批量入库
        failedData.add(allData.get(0));
        allData.clear();
        this.setReadCompleted(true);
    }


}
