package mypackage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Global {
    public static final Path FILEPATH = Paths.get(".", "tasks.txt");
    private static String line = "-----------------------------------------------------";
    public static void printline() {
        System.out.println(line);
    }

    public static String getline() {
        return Global.line;
    }
}
