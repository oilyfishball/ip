package ackermann;

import ackermann.exceptions.InvalidTargetException;
import ackermann.task.Task;

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
     * Removes a task from the list
     *
     * @param i index of the task to remove
     */
    public void remove(int i) {
        this.tasks.remove(i);
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
}
