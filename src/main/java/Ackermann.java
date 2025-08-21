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
                //Level-1
                break;
            } else if (input.equals("list")) {
                //Level-2
                storage.list();
            } else if (input.contains("mark")) {
                //Level-3
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
                String[] words = input.split(" ", 2);

                switch (words[0]) {
                    case "todo":
                        storage.addtoDo(words[1]);
                        break;
                    case "deadline":
                        storage.adddeadline(words[1]);
                        break;
                    case "event":
                        storage.addevent(words[1]);
                        break;
                    default:
                        storage.store(input);
                        break;
                }
            }
            Global.printline();
        }
        Startup.print_end();
    }
}
