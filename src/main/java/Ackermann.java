import Package.Startup.*;

public class Ackermann {
    public static void printline() {
        System.out.println("-----------------------------------------------------");
    }
    public static void main(String[] args) {
        Ackermann.printline();
        System.out.println(Startup.get_startup());
        Ackermann.printline();
        System.out.println(Startup.get_end());
        Ackermann.printline();
    }
}
