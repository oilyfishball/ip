package Package.Task;

import Package.Task.Task;

public class ToDos extends Task {
    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
