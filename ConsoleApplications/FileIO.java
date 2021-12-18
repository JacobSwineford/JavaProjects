package Misc.ConsoleApplications;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * @author Jacob Swineford
 */
public class FileIO {

    public static void main(String[] args) throws Exception {
//        String dir = "../JavaProjects";
//        String toAdd = "../JavaProjects/src/Misc/newFile.txt";
//        File dire = new File(dir);
//        File add = new File(toAdd);
//        if (dire.exists()) {
//            System.out.println("the file exists.");
//        }
//        System.out.println(Arrays.toString(dire.list()));
//        boolean b = add.createNewFile();
//        if (b) System.out.println("created new file");
//        else System.out.println("did not create new file");
        String fileName = "sameplePNG.png";
        System.out.println(fileName.substring(fileName.lastIndexOf('.') + 1));
    }
}
