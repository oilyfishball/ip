package Package.Startup;

public class Startup {
    private static String startup_message = "Hello! I'm Ackermann\nWhat can I do for you?";
    private static String end_message = "Bye. Hope to see you again soon!";

    public static String get_startup() {
        return Startup.startup_message;
    }

    public static String get_end() {
        return Startup.end_message;
    }
}