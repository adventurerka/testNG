package test;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Paths;

public class FolderCreationFixture {

    @BeforeMethod
    public void readOnlyFolderCreation (Method m) {
        TempDir ignore = m.getAnnotation(TempDir.class);
        if (ignore != null) {
            File file = new File(Paths.get("").toAbsolutePath().toString()+"/new/new2/");
            file.mkdir();
            file.setWritable(ignore.write());
            file.setReadable( ignore.read());
        }
    }
}
