package com.github.kyo7701.poi;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class POITest {

    public static void main(String[] args) throws IOException {
//        List<List<String>> header = mockTemplateWithData();
//        export2Excel(header);
        readExcel();
    }


    private static void readExcel() throws IOException {
        String fileName = "/Users/mr_cris/Desktop/1.xlsx";
        String sheetName = "sheet1";
        String templateId = "akkkk";
        List<Map> failedData = new ArrayList<>();
        ExcelReaderListener listener = new ExcelReaderListener(new BasicPlainRowTemplateStrategy(), templateId, failedData);
        ExcelReader excelReader = EasyExcelFactory.read(new FileInputStream(fileName), listener).build();
        ReadSheet readSheet = EasyExcelFactory.readSheet(sheetName).build();
        readSheet.setHeadRowNumber(0);
        excelReader.read(readSheet);
        System.out.println("finish");
        excelReader.finish();
    }

    private static void export2Excel(List<List<String>> data) throws IOException {
        String fileName = "/Users/mr_cris/Desktop/1.xlsx";
        String sheetName = "sheet1";
        FileOutputStream outputStream = new FileOutputStream(fileName);
        ExcelWriter excelWriter = EasyExcelFactory.write(outputStream).build();
        WriteSheet writeSheet = EasyExcelFactory.writerSheet(sheetName).build();
        excelWriter.write(data, writeSheet);
        excelWriter.finish();


    }

    private static List<List<String>> mockTemplate() {
        List<List<String>> headers = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("a1");
        header.add("a2");
        header.add("a3");
        headers.add(header);
        List<String> header2 = new ArrayList<>();
        header2.add("列1");
        header2.add("列2");
        header2.add("列3");
        headers.add(header2);
        return headers;
    }

    private static List<List<String>> mockTemplateWithData() {
        List<List<String>> headers = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("a1");
        header.add("a2");
        header.add("a3");
        headers.add(header);
        List<String> header2 = new ArrayList<>();
        header2.add("列1");
        header2.add("列2");
        header2.add("列3");
        headers.add(header2);
        for (int i = 0; i < 10; i++) {
            String value1 = "value1:" + Math.random() * 100 + "";
            String value2 = "value2:" + Math.random() * 100 + "";
            String value3 = "value3:" + Math.random() * 100 + "";
            List<String> dataRow = new ArrayList<>();
            dataRow.add(value1);
            dataRow.add(value2);
            dataRow.add(value3);
            headers.add(dataRow);
        }
        return headers;
    }

}
