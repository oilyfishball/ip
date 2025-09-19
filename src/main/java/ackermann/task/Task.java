package ackermann.task;

import java.util.ArrayList;
import java.util.List;

import ackermann.exceptions.task.EmptyNameException;

/**
 * Superclass for all Task objects
 */
public class Task {
    private boolean isDone;
    private String name;
    private List<String> tags;

    /**
     * Constructor for Task
     * @param name
     */
    public Task(String name) throws EmptyNameException {
        if (name.trim().isEmpty()) {
            throw new EmptyNameException();
        }
        this.isDone = false;
        this.name = name;
        this.tags = new ArrayList<>();
    }

    /**
     * Formats boolean flag to string
     * @return X if isDone " " if undone
     */
    public String getStatus() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void tag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.name;
    }
}
