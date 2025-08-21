package Package.Task;

public class Task {
    private boolean done;
    private String name;

    public Task(String name) {
        this.done = false;
        this.name = name;
    }

    public String getStatus() {
        return done ? "X" : " ";
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.name;
    }
}
