package ackermann;

import ackermann.exceptions.InvalidFindException;
import ackermann.exceptions.InvalidTargetException;
import ackermann.task.Deadlines;
import ackermann.task.Events;
import ackermann.task.Task;
import ackermann.task.ToDos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * List to store tasks
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for TaskList
     *
     * @param tasks list of tasks to keep track of
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for Tasklist
     * Initialised by empty array list
     * Used for first time users
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * @return number of tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets a task from the list
     *
     * @param i index of the task to retrieve
     * @return A task object
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds a task from the list
     *
     * @param task Task to append to the list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Lists all tasks
     *
     * @return A string of all tasks
     */
    public String list() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            int curr = i + 1;

            str.append(curr).append(". ").append(tasks.get(i));
            if (i != this.tasks.size() - 1) {
                str.append("\n");
            }
        }

        return String.valueOf(str);
    }

    /**
     * Marks a task as done and prints success message
     *
     * @param i Key to task in list
     */
    public void mark(int i) throws InvalidTargetException {
        try {
            this.tasks.get(i).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.tasks.get(i).toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

    /**
     * Marks a task as not done and prints success message
     *
     * @param i Key to task in list
     */
    public void unmark(int i) throws InvalidTargetException {
        try {
            this.tasks.get(i).markAsUndone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(this.tasks.get(i).toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

    /**
     * Deletes a task from the list
     * @param id Key to access the task to delete
     */
    public void delete(int id) throws InvalidTargetException {
        try {
            Task currTask = tasks.get(id);
            tasks.remove(id);
            System.out.println("Noted, I've removed this task:");
            System.out.println(currTask.toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

    /**
     * Adds a todo task to list and prints success message
     * @param input Name of task
     */
    public void addToDo(String input) {
        Task toDo = new ToDos(input);
        this.tasks.add(toDo);
        System.out.println("Got it. I've added this ToDo:\n" + toDo);
    }

    /**
     * Adds a deadline task to list and prints success message
     * @param name Name of task
     * @param by Deadline of task
     */
    public void addDeadline(String name, LocalDate by) {
        Task deadline = new Deadlines(name, by);
        this.tasks.add(deadline);
        System.out.println("Got it. I've added this Deadline:\n" + deadline);
    }

    /**
     * Adds a event task to list and prints success message
     * @param name Name of task
     * @param from Start date of task
     * @param to End date of task
     */
    public void addEvent(String name, LocalDate from, LocalDate to) {
        Task event = new Events(name, from, to);
        this.tasks.add(event);
        System.out.println("Got it. I've added this Event:\n" + event);
    }

    /**
     * Searches the task list for matching keyword
     * Works regardless of keyword case
     * @param keyword Search query
     * @return A list of tasks that users search
     * @throws InvalidFindException
     */
    public List<Task> find(String keyword) throws InvalidFindException {
        List<Task> results = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task.getName().toUpperCase().contains(keyword.toUpperCase())) {
                results.add(task);
            }
        }

        if (results.isEmpty()) {
            throw new InvalidFindException();
        } else {
            return results;
        }
    }
}
