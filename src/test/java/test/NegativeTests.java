package test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NegativeTests extends FolderCreationFixture{


    Path currentRelativePath = Paths.get("");
    String path = currentRelativePath.toAbsolutePath().toString()+"/new/";
    File file = new File(path);

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        file.mkdir();
    }

    @Test(groups = "negative", enabled = false)
    public void createFileWithEmptyName() throws IOException {
        FileCreator fc = new FileCreator();
        fc.createNewFile(path+" ", "");
        for (File s:file.listFiles())
        System.out.println("List: "+s);
        Assert.assertTrue(file.listFiles().length==1);
    }

    @Test(groups = "negative", alwaysRun = true)
    @TempDir(read = true, write = false)
    public void cannotCreateFileInAReadOnlyDir() {
        FileCreator fc = new FileCreator();
        boolean mistake = false;
        try {
            fc.createNewFile(path+"new2/"+"bfdz.txt", "");
        } catch (IOException e) {
            e.printStackTrace();
            mistake = true;
        }
        Assert.assertTrue(mistake);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        new FileCreator().deleteDir(file);
    }

}
