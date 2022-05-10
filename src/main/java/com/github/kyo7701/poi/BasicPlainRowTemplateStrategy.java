package com.github.kyo7701.poi;

import java.util.HashMap;
import java.util.Map;

public class BasicPlainRowTemplateStrategy implements ReaderStrategy {

    @Override
    public Map<Integer, String> readHeader(Map<Integer, String> header) {
        Map<Integer, String> headerMap = new HashMap<>();
        int rowCount = header.keySet().size();
        for (int i = 0; i < rowCount; i++) {
            String v = header.get(i);
            headerMap.put(i, v);
        }
        return headerMap;
    }

    @Override
    public Map<String, String> readData(Map<Integer, String> header, Map<Integer, String> data) {
        Map<String, String> dataRow = new HashMap<>();
        int rowCount = data.keySet().size();
        for (int i = 0; i < rowCount; i++) {
            String k = header.get(i);
            String v = data.get(i);
            dataRow.put(k, v);
        }
        return dataRow;
    }

    @Override
    public boolean currentRowIsHeaderRegion(Integer rowIndex) {
        return rowIndex == 0 || rowIndex == 1;
    }

    @Override
    public boolean currentRowIsDataRegion(Integer rowIndex) {
        return !currentRowIsHeaderRegion(rowIndex);
    }

    @Override
    public boolean currentRowIsMetaData(Integer rowIndex) {
        return rowIndex == 0;
    }
}
