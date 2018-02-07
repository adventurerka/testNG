package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class PositiveTests {

    String path = "src/main/resources/new/";
    File file = new File(path);

    @BeforeClass
    public void setUp() {
        file.mkdir();
    }

    @DataProvider
    public Iterator<Object[]> fileContent() {
         ContentDataBase contentDataBase = new ContentDataBase();
         return contentDataBase.fileContent().iterator();
    }

    @Test(dataProvider = "fileContent")
    public void createFileWithData(String fileContent) throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(path+fileContent+".txt", fileContent);
    }

    @Test
    public void createFileAndChangeData () throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(path+"file2.txt","POTATO!!!");
        fc.createNewFile(path+"file2.txt", "APPLE!!!");
    }

    @AfterClass
    public void tearDown() {
    new FileCreator().deleteDir(file);
    }


}
