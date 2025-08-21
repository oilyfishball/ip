import Package.Startup.*;
import Package.Echo.*;
import Package.Global.*;

import java.util.Scanner;

public class Ackermann {
    public static void startup() {

    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        Startup.print_startup();

        Echo.echo();

        Startup.print_end();
    }
}
