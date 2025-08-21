package Package.Startup;

import Package.Global.Global;

public class Startup {
    private static String startup_message = "Hello! I'm Ackermann\nWhat can I do for you?";
    private static String end_message = "Bye. Hope to see you again soon!";

    public static String get_startup() {
        return Global.getline() + " \n" + Startup.startup_message + "\n" + Global.getline();
    }

    public static void print_startup() {
        System.out.println(Startup.get_startup());
    }

    public static String get_end() {
        return Global.getline() + " \n" + Startup.end_message + "\n" + Global.getline();    }

    public static void print_end() {
        System.out.println(Startup.get_end());
    }
}