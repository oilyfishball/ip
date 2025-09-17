package ackermann.task;

/**
 * Class to create toDo object
 */
public class ToDo extends Task {
    /**
     * Constructor for toDo task
     * @param name Name of task
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + super.getTags();
    }
}
