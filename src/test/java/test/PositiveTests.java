package test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PositiveTests {

    Path currentRelativePath = Paths.get("");
    String path = currentRelativePath.toAbsolutePath().toString()+"/new/";
    File file = new File(path);

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        file.mkdir();
    }

    @DataProvider
    public Iterator<Object[]> fileContent() {
        List mylist = new ArrayList<Object[]>();
        for(int i=0;i<10;i++){
             mylist.add(new Object[]{new FileCreator().randomName(7),
                     new FileCreator().randomName(7)});
         }
         return mylist.iterator();
    }

    @Test(groups =  {"positive", "stop"},dataProvider = "fileContent")
    public void createFileWithData(String fileContent, String name) throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(path+name+".txt", fileContent);
        Assert.assertEquals(fc.readFile(path+name+".txt"), fileContent);
    }

    @Test(groups = "positive",dataProvider = "excelDataProvider", dataProviderClass = ExcelDataProvider.class)
    @ExcelDataSource(value = "src/test/resources/MyFirstExcel.xls",sheetName = "Datatypes in Java")
    public void createFileWithData2(String par1, String par2, String par3) throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(path+par1+".txt",
                "DataType " + par1 + " Type " + par2 + " Size(in bytes) " + par3);
        System.out.println("DataType " + par1 + " Type " + par2 + " Size(in bytes) " + par3);
        Assert.assertEquals(fc.readFile(path+par1+".txt"),
                "DataType " + par1 + " Type " + par2 + " Size(in bytes) " + par3);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "fileName",
            groups = {"positive", "stop"})
    public void createFileAndChangeData (String name) throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(path+name+".txt","POTATO!!!");
        fc.createNewFile(path+name+".txt", "APPLE!!!");
        Assert.assertEquals(fc.readFile(path+name+".txt"),"APPLE!!!");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
    new FileCreator().deleteDir(file);
    }


}
