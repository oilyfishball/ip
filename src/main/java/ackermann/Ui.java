package ackermann;

import ackermann.exceptions.InvalidFindException;
import ackermann.exceptions.InvalidTargetException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineByException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventFromException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventToException;
import ackermann.task.Deadlines;
import ackermann.task.Events;
import ackermann.task.Task;
import ackermann.task.ToDos;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Deals with operations based on users' input
 */
public class Ui {
    public void list(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks.list());
    }

    public void showLoadingError() {
        System.out.println("Error loading saved info!");
    }

    /**
     * Marks a task as completed
     *
     * @param tasks List to find task.
     * @param i     Key to find task.
     * @throws InvalidTargetException
     */
    public void mark(TaskList tasks, int i) throws InvalidTargetException {
        int id = i - 1;
        tasks.mark(id);
    }

    /**
     * Marks a task as uncompleted
     *
     * @param tasks List to find task.
     * @param i     Key to find task.
     * @throws InvalidTargetException
     */
    public void unmark(TaskList tasks, int i) throws InvalidTargetException {
        int id = i - 1;
        tasks.unmark(id);
    }

    /**
     * Deletes a task from list
     *
     * @param tasks List to delete task from.
     * @param i     Key to find task.
     * @throws InvalidTargetException
     */
    public void delete(TaskList tasks, int i) throws InvalidTargetException {
        int id = i - 1;
        tasks.delete(id);
        this.printRemaining(tasks);
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
    public void addToDo(TaskList tasks, String input) {
        tasks.addToDo(input);
        this.printRemaining(tasks);
    }

    /**
     * Adds deadline tasks to list of tasks
     *
     * @param tasks List of tasks to add result to.
     * @param input Name of the task, and deadline to be completed by.
     *              Takes on the form "something /by something".
     */
    public void addDeadline(TaskList tasks, String input) throws InvalidDeadlineException {
        String[] info = input.split("/by ", 2);
        if (info.length < 2) {
            throw new InvalidDeadlineByException();
        }
        try {
            LocalDate date = LocalDate.parse(info[1]);
            tasks.addDeadline(info[0], date);
            this.printRemaining(tasks);
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
    public void addEvent(TaskList tasks, String input) throws InvalidEventException {
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
            tasks.addEvent(info[0], from, to);
            this.printRemaining(tasks);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date!\nFollow the format 'YYYY-MM-DD'.");
        }
    }

    /**
     * Finds tasks of which substring of names is equals to keyword
     *
     * @param keyword keyword to match the name of tasks
     * @return A string of tasks
     */
    public String find(TaskList tasks, String keyword) {
        try {
            List<Task> foundTasks = tasks.find(keyword);
            StringBuilder result = new StringBuilder();
            result.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                result.append(foundTasks.get(i).toString());

                if (i != foundTasks.size() - 1) {
                    result.append("\n");
                }
            }

            return String.valueOf(result);
        } catch (InvalidFindException e) {
            System.out.print(e.getMessage());
            return "";
        }
    }
}
