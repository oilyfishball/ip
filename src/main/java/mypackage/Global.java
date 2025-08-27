package mypackage;

public class Global {
    private static String line = "-----------------------------------------------------";
    public static void printline() {
        System.out.println(line);
    }

    public static String getline() {
        return Global.line;
    }
}
