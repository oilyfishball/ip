package ackermann;

import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidCodeException;
import ackermann.exceptions.InvalidDeleteException;
import ackermann.exceptions.InvalidFindException;
import ackermann.exceptions.InvalidMarkException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventException;
import ackermann.exceptions.task.InvalidTodo.InvalidTodoException;

import static java.lang.Integer.parseInt;

/**
 * Parser to parse codewords with values
 */
public class Parser {
    private enum Codewords {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND;

        public static Parser.Codewords check(String x) throws InvalidCodeException {
            try {
                if (x.toLowerCase().equals(x)) {
                    return Parser.Codewords.valueOf(x.toUpperCase());
                } else {
                    throw new InvalidCodeException();
                }
            } catch (IllegalArgumentException e) {
                throw new InvalidCodeException();
            }
        }
    }

    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor of Parser
     * @param ui UI to use for chatbot operations
     * @param tasks task list to update to/ retrieve info from
     */
    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Parses the codeword and details to perform operation
     * @param input
     * @return A boolean on whether or not the program should terminate
     * @throws CheckedException
     */
    public boolean parse(String input) throws CheckedException {
        String[] words = input.split(" ", 2);
        Parser.Codewords codeword = Parser.Codewords.check(words[0]);

        switch (codeword) {
        case BYE:
            return false;
        case LIST:
            ui.list(tasks);
            break;
        case MARK:
        case UNMARK:
            try {
                int target = parseInt(words[1]);
                String mark = words[0];

                if (mark.equals("mark")) {
                    ui.mark(tasks, target);
                } else if (mark.equals("unmark")) {
                    ui.unmark(tasks, target);
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
                ui.delete(tasks, target);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidDeleteException();
            }
            break;
        case TODO:
            if (words.length == 1 || words[1].isEmpty()) {
                throw new InvalidTodoException();
            }
            ui.addToDo(this.tasks, words[1]);
            break;
        case DEADLINE:
            if (words.length == 1 || words[1].isEmpty()) {
                throw new InvalidDeadlineException();
            }
            ui.addDeadline(this.tasks, words[1]);
            break;
        case EVENT:
            if (words.length == 1 || words[1].isEmpty()) {
                throw new InvalidEventException();
            }
            ui.addEvent(this.tasks, words[1]);
            break;
        case FIND:
            if (words.length == 1 || words[1].isEmpty()) {
                throw new InvalidFindException();
            }
            System.out.println(ui.find(tasks, words[1]));
            break;
        default:
            throw new InvalidCodeException();
        }
        return true;
    }
}
