package test;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public static Iterator<Object[]> fileName() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
        DataProvider.class.getResourceAsStream("/fileName.data")));

        List<Object[]> fileName = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line!=null) {
            fileName.add(new Object[]{line});
            line = in.readLine();
        }
        in.close();

        return fileName.iterator();
    }
}
