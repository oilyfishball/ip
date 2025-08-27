package ackermann;

import ackermann.exceptions.InvalidTargetException;
import ackermann.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for TaskList
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
     *
     * @return number of tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets a task from the list
     * @param i index of the task to retrieve
     * @return A task object
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Removes a task from the list
     * @param i index of the task to remove
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Adds a task from the list
     * @param task Task to append to the list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }
}
