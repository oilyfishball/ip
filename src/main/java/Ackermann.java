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
    private enum Codewords {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;

        public static Codewords check(String x) throws InvalidCodeException {
            try {
                if (x.toLowerCase().equals(x)) {
                    return Codewords.valueOf(x.toUpperCase());
                } else {
                    throw new InvalidCodeException();
                }
            } catch (IllegalArgumentException e) {
                throw new InvalidCodeException();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store storage = new Store();
        boolean run = true;

        Startup.print_startup();
        while (run) {
            try {
                String input = scanner.nextLine();
                Global.printline();
                String[] words = input.split(" ", 2);
                Codewords codeword = Codewords.check(words[0]);

                switch(codeword) {
                    case BYE:
                        run = false;
                        break;
                    case LIST:
                        storage.list();
                        break;
                    case MARK:
                    case UNMARK:
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
                        break;
                    case DELETE:
                        try {
                            int target = parseInt(words[1]);
                            storage.delete(target);
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            throw new InvalidDeleteException();
                        }
                        break;
                    case TODO:
                        if (words.length == 1 || words[1].isEmpty()) {
                            throw new InvalidTodoException();
                        }
                        storage.addtoDo(words[1]);
                        break;
                    case DEADLINE:
                        if (words.length == 1 || words[1].isEmpty()) {
                            throw new InvalidDeadlineException();
                        }
                        storage.adddeadline(words[1]);
                        break;
                    case EVENT:
                        if (words.length == 1 || words[1].isEmpty()) {
                            throw new InvalidEventException();
                        }
                        storage.addevent(words[1]);
                        break;
                    default:
//                        storage.store(input);
                        throw new InvalidCodeException();
                }
                if (run) Global.printline();
            }

            catch (CheckedException e) {
                System.out.println(e.getMessage());
                Global.printline();
            }
        }
        Startup.print_end();
    }
}
