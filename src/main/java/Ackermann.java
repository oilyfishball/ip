import java.util.Scanner;
import Package.*;

import static java.lang.Integer.parseInt;

public class Ackermann {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store storage = new Store();

        Startup.print_startup();
        while (true) {
            String input = scanner.nextLine();

            Global.printline();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                storage.list();
            } else if (input.contains("mark")) {
                String[] words = input.split(" ");
                int target = parseInt(words[1]);
                String mark = words[0];

                if (mark.equals("mark")) {
                    storage.mark(target);
                } else if (mark.equals("unmark")) {
                    storage.unmark(target);
                }
            }
            else {
//                Echo.echo(input);
                storage.store(input);
            }
            Global.printline();
        }
        Startup.print_end();
    }
}
