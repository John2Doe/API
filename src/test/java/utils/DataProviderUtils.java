package utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderUtils {


    @DataProvider(name = "DataFromExcel")
    public static Object[][] getDataFromExcelWithDataProvider(Method method){
        ExcelUtils.openExcelFile("Book2", "Sheet1");
        Object[][] arrayObject = ExcelUtils.getExcelData(ExcelUtils.getValues(method.getName()));
        ExcelUtils.closeExcelFile();
        return arrayObject;
    }
}
