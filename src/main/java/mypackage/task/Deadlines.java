package mypackage.task;

public class Deadlines extends Task{
    private String by;

    public Deadlines(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + this.by + ")";
    }

    public String getBy() {
        return this.by;
    }
}
