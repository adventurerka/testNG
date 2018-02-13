package test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelDataProvider {

    @DataProvider
    public static Object[][] excelDataProvider(Method m) {
        if (m.isAnnotationPresent(ExcelDataSource.class)) {
            int length = m.getParameterTypes().length;
            ExcelDataSource dataSource = m.getAnnotation(ExcelDataSource.class);
            File xlsFile = new File(dataSource.value());
            String sheetName = dataSource.sheetName();
            List<Object[]> result = new ArrayList<Object[]>();
            try {
                FileInputStream excelFile = new FileInputStream(xlsFile);
                Workbook workbook = new HSSFWorkbook(excelFile);
                Sheet datatypeSheet = workbook.getSheet(sheetName);
                Iterator<Row> iterator = datatypeSheet.iterator();

                while (iterator.hasNext()) {
                    Object[] parameters = new Object[length];
                    Row currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();

                    for (int i = 0; i < length; i++) {
                        Cell currentCell = cellIterator.next();
                        if (currentCell.getCellTypeEnum() == CellType.STRING) {
                            parameters[i] = currentCell.getStringCellValue();
                        }
                    }
                    result.add(parameters);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toArray(new Object[result.size()][]);
        } else {
            throw new Error("There is no @ExcelDataSource annotation on method " + m);
        }
    }

}
