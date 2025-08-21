package Package;

public class Store {
    private Task[] tasks = new Task[100];
    private int idx = 0;

    public void list() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < this.tasks.length; i++) {
            if (this.tasks[i] == null) {
                break;
            }
            int curr = i + 1;

            System.out.println(curr + ". " + this.tasks[i]);
        }
    }

    public void store(String input) {
        Task task = new Task(input);
        this.tasks[this.idx] = task;
        this.idx += 1;

        System.out.println("added: " + input);
    }

    public void mark(int i) {
        int id = i - 1;
        Task currTask = tasks[id];
        currTask.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(currTask.toString());
    }

    public void unmark(int i) {
        int id = i - 1;
        Task currTask = tasks[id];
        currTask.markAsUndone();
        System.out.println("Ok, I've marked this task as not done yet: ");
        System.out.println(currTask.toString());
    }
}
