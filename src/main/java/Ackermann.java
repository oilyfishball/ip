import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import exceptions.CheckedException;
import exceptions.InvalidCodeException;
import exceptions.InvalidDeleteException;
import exceptions.mark.InvalidMarkException;
import exceptions.task.InvalidDeadline.InvalidDeadlineException;
import exceptions.task.InvalidEvent.InvalidEventException;
import exceptions.task.InvalidTodo.InvalidTodoException;
import mypackage.*;

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

    private static void startUp(Store storage) throws CheckedException{
        try {
            Scanner fileIn = new Scanner(new File(String.valueOf(Global.FILEPATH)));
            int curr = 1;

            while (fileIn.hasNext()) {
                String next = fileIn.nextLine();
                //scans next line
                String[] taskStr = next.split(" \\| ");
                String type = taskStr[0];
                boolean status = taskStr[1].equals("1");
                String value = taskStr[2];
                switch (type) {
                case "T":
                    storage.addToDo(value, false);
                    if (status) storage.mark(curr, false);
                    break;
                case "D":
                    storage.addDeadline(value, false);
                    if (status) storage.mark(curr, false);
                    break;
                case "E":
                    storage.addEvent(value, false);
                    if (status) storage.mark(curr, false);
                    break;
                default:
                    throw new InvalidCodeException();
                }
                curr += 1;
            }
        } catch (FileNotFoundException e) {
            File newFile = new File(String.valueOf(Global.FILEPATH));
            try {
                boolean success = newFile.createNewFile();
            } catch (IOException ex) {
                System.out.println("Error creating new file!");
            }
        } finally {
            storage.list();
            Global.printline();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store storage = new Store();
        boolean run = true;
        boolean init = true;

        Startup.print_startup();

        while (run) {
            try {
                if (init) {
                    startUp(storage);
                    init = false;
                }
                String input = scanner.nextLine();
                Global.printline();
                String[] words = input.split(" ", 2);
                Codewords codeword = Codewords.check(words[0]);

                switch (codeword) {
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
                    storage.addToDo(words[1]);
                    break;
                case DEADLINE:
                    if (words.length == 1 || words[1].isEmpty()) {
                        throw new InvalidDeadlineException();
                    }
                    storage.addDeadline(words[1]);
                    break;
                case EVENT:
                    if (words.length == 1 || words[1].isEmpty()) {
                        throw new InvalidEventException();
                    }
                    storage.addEvent(words[1]);
                    break;
                default:
//                        storage.store(input);
                    throw new InvalidCodeException();
                }
                if (run) Global.printline();
            } catch (CheckedException e) {
                System.out.println(e.getMessage());
                Global.printline();
            }
        }
        Startup.print_end();
    }
}
