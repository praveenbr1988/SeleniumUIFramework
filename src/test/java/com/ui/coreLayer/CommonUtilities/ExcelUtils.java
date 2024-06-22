package com.ui.coreLayer.CommonUtilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    private static final String FILE_PATH = "src/main/resources/testData/testdatasheet.xlsx";

    public static Object[][] getTestData(String sheetName) {
        Object[][] testData = null;
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            testData = new Object[rowCount][];
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                List<Object> rowData = new ArrayList<>();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    rowData.add(cell.getStringCellValue());
                }
                testData[i - 1] = rowData.toArray();
            }
            workbook.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }
}
