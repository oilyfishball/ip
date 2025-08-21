package Package;

import Exceptions.Mark.InvalidTargetException;
import Exceptions.Task.InvalidDeadline.InvalidDeadlineByException;
import Exceptions.Task.InvalidDeadline.InvalidDeadlineException;
import Exceptions.Task.InvalidEvent.InvalidEventException;
import Exceptions.Task.InvalidEvent.InvalidEventFromException;
import Exceptions.Task.InvalidEvent.InvalidEventToException;
import Exceptions.Task.InvalidTaskException;
import Package.Task.Deadlines;
import Package.Task.Events;
import Package.Task.Task;
import Package.Task.ToDos;

public class Store {
    private Task[] tasks = new Task[100];
    private int idx = 0;

    public void list() {
        System.out.println("Here are the tasks in your list:");
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

    public void mark(int i) throws InvalidTargetException {
        try {
            int id = i - 1;
            Task currTask = tasks[id];
            currTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currTask.toString());
        } catch (NullPointerException e) {
            throw new InvalidTargetException();
        }
    }

    public void unmark(int i) throws InvalidTargetException {
        try {
            int id = i - 1;
            Task currTask = tasks[id];
            currTask.markAsUndone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(currTask.toString());
        } catch (NullPointerException e) {
            throw new InvalidTargetException();
        }
    }

    public void printAdd(Task task) {
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have " + this.idx + " tasks in the list.");
    }
    public void addtoDo(String input) {
        Task toDo = new ToDos(input);
        this.tasks[this.idx] = toDo;
        this.idx += 1;

        this.printAdd(toDo);
    }

    public void adddeadline(String input) throws InvalidDeadlineException {
        String[] info = input.split("/by ", 2);
        if (info.length < 2) {
            throw new InvalidDeadlineByException();
        }
        Task deadline = new Deadlines(info[0], info[1]);
        this.tasks[this.idx] = deadline;
        this.idx += 1;

        this.printAdd(deadline);
    }

    public void addevent(String input) throws InvalidEventException {
        String[] info = input.split("/from ", 2);
        if (info.length < 2) {
            throw new InvalidEventFromException();
        }

        String[] info2 = info[1].split("/to ", 2);
        if (info2.length < 2) {
            throw new InvalidEventToException();
        }

        Task event = new Events(info[0], info2[0], info2[1]);
        this.tasks[this.idx] = event;
        this.idx += 1;

        this.printAdd(event);
    }


}
