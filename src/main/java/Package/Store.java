package Package;

import java.util.Scanner;

public class Store {
    public static void store() {
        Scanner userInput = new Scanner(System.in);
        String[] tasks = new String[100];
        int idx = 0;

        while (true) {
            String input = userInput.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                Global.printline();
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    int curr = i + 1;
                    System.out.println(curr + ". " + tasks[i]);
                }
                Global.printline();
            } else {
                tasks[idx] = input;
                idx += 1;

                Global.printline();
                System.out.println("added: " + input);
                Global.printline();
            }
        }
    }
}
