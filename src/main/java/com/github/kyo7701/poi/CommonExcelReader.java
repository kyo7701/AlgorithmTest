package com.github.kyo7701.poi;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public abstract class CommonExcelReader {

    static Logger logger = LoggerFactory.getLogger(CommonExcelReader.class);

    private static void readExcel(String templateId, ReaderStrategy strategy, InputStream inputStream, String sheetName, List<Map> failedData) throws IOException {
        ExcelReaderListener listener = new ExcelReaderListener(strategy, templateId, failedData);
        ExcelReader excelReader = EasyExcelFactory.read(inputStream, listener).build();
        ReadSheet readSheet = EasyExcelFactory.readSheet(sheetName).build();
        readSheet.setHeadRowNumber(0);
        excelReader.read(readSheet);
        logger.info("finish read excel with sheet {}", sheetName);
        excelReader.finish();
    }


}
