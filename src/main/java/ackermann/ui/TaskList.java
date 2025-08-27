package ackermann.ui;

import ackermann.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> prompt) {
        this.tasks = prompt;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }
}
