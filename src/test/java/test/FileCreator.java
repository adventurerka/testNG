package test;

import java.io.*;

public class FileCreator {

    public void createNewFile(String name, String text) throws IOException {
        File statText = new File(name);
        statText.createNewFile();
        FileOutputStream is = new FileOutputStream(statText);
        OutputStreamWriter osw = new OutputStreamWriter(is);
        Writer w = new BufferedWriter(osw);
        w.write(text);
        w.close();
    }

    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir (new File(dir, children[i]));

                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }


}
