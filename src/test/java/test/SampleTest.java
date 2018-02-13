package test;

import org.testng.annotations.Test;

public class SampleTest {

    @Test(dataProvider = "excelDataProvider", dataProviderClass = ExcelDataProvider.class)
    @ExcelDataSource(value = "src/test/resources/MyFirstExcel.xls",sheetName = "Datatypes in Java")
    public void testXls(String par1, String par2, String par3) {
        System.out.println("DataType " + par1 + " Type " + par2 + " Size(in bytes) " + par3);
    }
}
