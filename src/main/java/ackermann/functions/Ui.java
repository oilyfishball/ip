package ackermann.functions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import ackermann.codewords.Codeword;
import ackermann.codewords.DeadlineCodeword;
import ackermann.codewords.DeleteCodeword;
import ackermann.codewords.EventCodeword;
import ackermann.codewords.FindCodeword;
import ackermann.codewords.ListCodeword;
import ackermann.codewords.MarkCodeword;
import ackermann.codewords.TodoCodeword;
import ackermann.codewords.UnmarkCodeword;
import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidFindException;
import ackermann.exceptions.InvalidTargetException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineByException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventFromException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventToException;

/**
 * Deals with operations based on users' input
 */
public class Ui {
    /**
     * Lists out all tasks
     * @param tasks tasks to list
     */
    public void list(TaskList tasks) throws CheckedException {
        Codeword codeword = new ListCodeword(tasks);
        System.out.println(codeword.execute());
    }

    /**
     * Shows loading error
     */
    public void showLoadingError() {
        System.out.println("Error loading saved info!");
    }

    /**
     * Marks a task as completed
     *
     * @param tasks List to find task.
     * @param i     Key to find task.
     * @throws CheckedException
     */
    public void mark(TaskList tasks, int i) throws CheckedException {
        int id = i - 1;
        Codeword codeword = new MarkCodeword(tasks, id);
        System.out.println(codeword.execute());
    }

    /**
     * Marks a task as uncompleted
     *
     * @param tasks List to find task.
     * @param i     Key to find task.
     * @throws InvalidTargetException
     */
    public void unmark(TaskList tasks, int i) throws CheckedException {
        int id = i - 1;
        Codeword codeword = new UnmarkCodeword(tasks, id);
        System.out.println(codeword.execute());
    }

    /**
     * Deletes a task from list
     *
     * @param tasks List to delete task from.
     * @param i     Key to find task.
     * @throws InvalidTargetException
     */
    public void delete(TaskList tasks, int i) throws CheckedException {
        int id = i - 1;
        Codeword codeword = new DeleteCodeword(tasks, id);
        System.out.println(codeword.execute());
    }

    /**
     * Prints number of remaining tasks
     *
     * @param tasks Tasklist to print
     */
    public void printRemaining(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds toDo task to list of tasks
     *
     * @param tasks List to add task to.
     * @param input Name of task.
     */
    public void addToDo(TaskList tasks, String input) throws CheckedException {
        Codeword codeword = new TodoCodeword(tasks, input);
        System.out.println(codeword.execute());
    }

    /**
     * Adds deadline tasks to list of tasks
     *
     * @param tasks List of tasks to add result to.
     * @param input Name of the task, and deadline to be completed by.
     *              Takes on the form "something /by something".
     */
    public void addDeadline(TaskList tasks, String input) throws CheckedException {
        String[] info = input.split("/by ", 2);
        if (info.length < 2) {
            throw new InvalidDeadlineByException();
        }
        try {
            LocalDate date = LocalDate.parse(info[1]);
            Codeword codeword = new DeadlineCodeword(tasks, info[0], date);
            System.out.println(codeword.execute());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date!\nFollow the format 'YYYY-MM-DD'.");
        }
    }

    /**
     * Adds event tasks to list of tasks
     *
     * @param tasks List of tasks to add result to.
     * @param input Name of the task, from when and to when.
     *              Takes on the form "something /from something /to something".
     */
    public void addEvent(TaskList tasks, String input) throws CheckedException {
        String[] info = input.split("/from ", 2);
        if (info.length < 2) {
            throw new InvalidEventFromException();
        }

        String[] info2 = info[1].split(" /to ", 2);
        if (info2.length < 2) {
            throw new InvalidEventToException();
        }
        try {
            LocalDate from = LocalDate.parse(info2[0]);
            LocalDate to = LocalDate.parse(info2[1]);
            Codeword codeword = new EventCodeword(tasks, info[0], from, to);
            System.out.println(codeword.execute());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date!\nFollow the format 'YYYY-MM-DD'.");
        }
    }

    /**
     * Finds tasks of which substring of names is equals to keyword
     * @param tasks tasks to find from
     * @param keyword keyword to match the name of tasks
     */
    public void find(TaskList tasks, String keyword) throws CheckedException {
        try {
            Codeword codeword = new FindCodeword(tasks, keyword);
            System.out.println(codeword.execute());
        } catch (InvalidFindException e) {
            System.out.print(e.getMessage());
        }
    }
}
