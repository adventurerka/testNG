package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class NegativeTests {

    String path = "src/main/resources/new/";
    File file = new File(path);

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        file.mkdir();
    }

    @Test(groups = "negative", alwaysRun = true)
    public void createFileWithEmptyName() throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(path+" ", "");
        for (File s:file.listFiles())
        System.out.println("List: "+s);
        System.out.println("createFileWithEmptyName");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        new FileCreator().deleteDir(file);
    }

}
