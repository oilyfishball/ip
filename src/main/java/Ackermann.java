import java.util.Scanner;

import Exceptions.CheckedException;
import Exceptions.InvalidCodeException;
import Exceptions.InvalidDeleteException;
import Exceptions.Mark.InvalidMarkException;
import Exceptions.Task.InvalidDeadline.InvalidDeadlineException;
import Exceptions.Task.InvalidEvent.InvalidEventException;
import Exceptions.Task.InvalidTodo.InvalidTodoException;
import Package.*;

import static java.lang.Integer.parseInt;

public class Ackermann {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store storage = new Store();

        Startup.print_startup();
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] words = input.split(" ", 2);

                Global.printline();
                if (input.equals("bye")) {
                    //Level-1
                    break;
                } else if (input.equals("list")) {
                    //Level-2
                    storage.list();
                } else if (words[0].contains("mark")) {
                    //Level-3
                    try {
                        int target = parseInt(words[1]);
                        String mark = words[0];

                        if (mark.equals("mark")) {
                            storage.mark(target);
                        } else if (mark.equals("unmark")) {
                            storage.unmark(target);
                        } else {
                            throw new InvalidCodeException();
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        throw new InvalidMarkException();
                    }
                } else if (words[0].equals("delete")) {
                    try {
                        int target = parseInt(words[1]);
                        storage.delete(target);
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        throw new InvalidDeleteException();
                    }
                } else {
//                Echo.echo(input);
                    switch (words[0]) {
                        case "todo":
                            if (words.length == 1 || words[1].isEmpty()) {
                                throw new InvalidTodoException();
                            }
                            storage.addtoDo(words[1]);
                            break;
                        case "deadline":
                            if (words.length == 1 || words[1].isEmpty()) {
                                throw new InvalidDeadlineException();
                            }
                            storage.adddeadline(words[1]);
                            break;
                        case "event":
                            if (words.length == 1 || words[1].isEmpty()) {
                                throw new InvalidEventException();
                            }
                            storage.addevent(words[1]);
                            break;
                        default:
//                        storage.store(input);
                            throw new InvalidCodeException();
                    }
                }
                Global.printline();
            }

            catch (CheckedException e) {
                System.out.println(e.getMessage());
                Global.printline();
            }
        }
        Startup.print_end();
    }
}
