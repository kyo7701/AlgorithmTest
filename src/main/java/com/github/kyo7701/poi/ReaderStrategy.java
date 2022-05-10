package com.github.kyo7701.poi;

import java.util.Map;

public interface ReaderStrategy {

    Map<Integer, String> readHeader(Map<Integer, String> header);

    Map<String, String> readData(Map<Integer, String> header, Map<Integer, String> data);

    boolean currentRowIsHeaderRegion(Integer rowIndex);

    boolean currentRowIsDataRegion(Integer rowIndex);

    boolean currentRowIsMetaData(Integer rowIndex);

}
