package ackermann;

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
        try {
            int id = i - 1;
            Task currTask = tasks.get(id);
            currTask.markAsUndone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(currTask.toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

    /**
     * Deletes a task from list
     *
     * @param tasks List to delete task from.
     * @param i     Key to find task.
     * @throws InvalidTargetException
     */
    public void delete(TaskList tasks, int i) throws InvalidTargetException {
        try {
            int id = i - 1;
            Task currTask = tasks.get(id);
            tasks.remove(id);
            System.out.println("Noted, I've removed this task:");
            System.out.println(currTask.toString());
            this.printRemaining(tasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

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
        Task toDo = new ToDos(input);
        tasks.add(toDo);

        System.out.println("Got it. I've added this ToDo:\n" + toDo);
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
            Task deadline = new Deadlines(info[0], date);
            tasks.add(deadline);

            System.out.println("Got it. I've added this Deadline:\n" + deadline);
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
            Task event = new Events(info[0], from, to);
            tasks.add(event);

            System.out.println("Got it. I've added this Event:\n" + event);
            this.printRemaining(tasks);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date!\nFollow the format 'YYYY-MM-DD'.");
        }
    }
}
