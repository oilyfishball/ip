package Package;

import java.util.Scanner;

public class Echo {
    public static void echo() {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                Global.printline();
                System.out.println(input);
                Global.printline();}
        }
    }
}
