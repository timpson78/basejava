package webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFile {

    public static void getFiles(File file, int level ) {
        String printLevels=printLevels(level);
        File[] listfl = file.listFiles();

        if (listfl != null) {
            if (file.isDirectory()) {
                for (File fl : listfl) {
                    if ( fl.isDirectory()) {
                      System.out.println(printLevels+"dir:" + fl.getName());
                      getFiles(fl, level + 1);
                    } else if (fl.isFile()){
                        System.out.println(printLevels+"file:" + file.getName());
                    }
                }
            }
        }
    }

    public static String printLevels(int levels){
        String levelString=new String();
        for (int i = 0; i < levels; i++) {
            levelString=levelString+"  ";
        }
        return levelString;
    }


    public static void main (String[] args) throws IOException {
       String filepath="..";
        File file=new File(filepath);
        getFiles(file,0);

      /*  try {
            System.out.println(file.getCanonicalPath());
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error",e);
        }

        File dir = new File("C:\\Users\\tim\\Desktop\\");
        System.out.println(dir.isDirectory());
        String[] list=dir.list();
        if (list!=null) {
            for (String file1 : list) {
                System.out.println(file1);
            }
        }
        FileInputStream  fis=null;
        try {
          fis = new FileInputStream(filepath);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException("Error",e );
        }
        finally {
            fis.close();
        }*/
    }



}
