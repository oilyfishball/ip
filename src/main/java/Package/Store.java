package Package;

import Exceptions.InvalidTargetException;
import Exceptions.Task.InvalidDeadline.InvalidDeadlineByException;
import Exceptions.Task.InvalidDeadline.InvalidDeadlineException;
import Exceptions.Task.InvalidEvent.InvalidEventException;
import Exceptions.Task.InvalidEvent.InvalidEventFromException;
import Exceptions.Task.InvalidEvent.InvalidEventToException;
import Package.Task.Deadlines;
import Package.Task.Events;
import Package.Task.Task;
import Package.Task.ToDos;

import java.util.ArrayList;

public class Store {
//    private Task[] tasks = new Task[100];
    private ArrayList<Task> tasks = new ArrayList<>();

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i) == null) {
                break;
            }
            int curr = i + 1;

            System.out.println(curr + ". " + this.tasks.get(i));
        }
    }

    public void store(String input) {
        Task task = new Task(input);
        this.tasks.add(task);
        System.out.println("added: " + input);
    }

    public void mark(int i) throws InvalidTargetException {
        try {
            int id = i - 1;
            Task currTask = tasks.get(id);
            currTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currTask.toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

    public void unmark(int i) throws InvalidTargetException {
        try {
            int id = i - 1;
            Task currTask = tasks.get(id);
            currTask.markAsUndone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(currTask.toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

    public void printRemaining() {
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }
    public void addtoDo(String input) {
        Task toDo = new ToDos(input);
        this.tasks.add(toDo);

        System.out.println("Got it. I've added this ToDo:\n" + toDo);
        this.printRemaining();
    }

    public void adddeadline(String input) throws InvalidDeadlineException {
        String[] info = input.split("/by ", 2);
        if (info.length < 2) {
            throw new InvalidDeadlineByException();
        }
        Task deadline = new Deadlines(info[0], info[1]);
        this.tasks.add(deadline);

        System.out.println("Got it. I've added this Deadline:\n" + deadline);
        this.printRemaining();
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
        this.tasks.add(event);
        System.out.println("Got it. I've added this Event:\n" + event);
        this.printRemaining();
    }

    public void delete(int i) throws InvalidTargetException{
        try {
            int id = i - 1;
            Task currTask = tasks.get(id);
            tasks.remove(id);
            System.out.println("Noted, I've removed this task:");
            System.out.println(currTask.toString());
            this.printRemaining();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }
}
