package ackermann.task;

public class ToDos extends Task {
    /**
     * Constructor for toDo task
     * @param name Name of task
     */
    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
