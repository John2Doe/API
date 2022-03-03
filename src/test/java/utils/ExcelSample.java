package utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelSample {

    public static void main(String[] args) {
        String excelFilePath = "testData/Book1.xlsx";

        try {
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            String FirstValue = sheet.getRow(0).getCell(0).getStringCellValue();
            System.out.println(FirstValue);

            int lasRowNum = sheet.getLastRowNum();
            System.out.println(lasRowNum);

            int lastColumnNum = sheet.getRow(1).getLastCellNum();
            System.out.println(lastColumnNum);
            System.out.println();

            for (int i = 0; i <= lasRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < lastColumnNum; j++) {
                    XSSFCell cell = row.getCell(j);
                    System.out.print(cell + " | ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}