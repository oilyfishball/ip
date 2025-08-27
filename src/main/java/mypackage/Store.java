package mypackage;

import exceptions.InvalidTargetException;
import exceptions.task.InvalidDeadline.InvalidDeadlineByException;
import exceptions.task.InvalidDeadline.InvalidDeadlineException;
import exceptions.task.InvalidEvent.InvalidEventException;
import exceptions.task.InvalidEvent.InvalidEventFromException;
import exceptions.task.InvalidEvent.InvalidEventToException;
import mypackage.task.Deadlines;
import mypackage.task.Events;
import mypackage.task.Task;
import mypackage.task.ToDos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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

    public void mark(int i) throws InvalidTargetException {
        this.mark(i, true);
    }

    public void mark(int i, boolean print) throws InvalidTargetException {
        try {
            int id = i - 1;
            Task currTask = tasks.get(id);
            currTask.markAsDone();
            save();

            if (print) {
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currTask.toString());
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

    public void unmark(int i) throws InvalidTargetException {
        this.unmark(i, true);
    }

    public void unmark(int i, boolean print) throws InvalidTargetException {
        try {
            int id = i - 1;
            Task currTask = tasks.get(id);
            currTask.markAsUndone();
            save();

            if (print) {
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(currTask.toString());
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

    public void delete(int i) throws InvalidTargetException {
        try {
            int id = i - 1;
            Task currTask = tasks.get(id);
            tasks.remove(id);
            System.out.println("Noted, I've removed this task:");
            System.out.println(currTask.toString());
            this.printRemaining();
            save();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

    public void printRemaining() {
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void save() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            String tempStr = getString(i);

            if (i == tasks.size() - 1) {
                str.append(tempStr);
            } else {
                str.append(tempStr).append("\n");
            }
        }
        try {
            FileWriter myWriter = new FileWriter(String.valueOf(Global.FILEPATH));
            myWriter.write(String.valueOf(str));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error Saving New Task");
        }
    }

    private String getString(int i) {
        Task currTask = tasks.get(i);
        boolean status = currTask.getStatus().equals("X");
        String tempStr = "";

        if (status) {
            tempStr += " | 1 | " + currTask.getName();
        } else {
            tempStr += " | 0 | " + currTask.getName();
        }

        if (currTask instanceof ToDos) {
            tempStr = "T" + tempStr;
        } else if (currTask instanceof Events) {
            Events tempTask = (Events) tasks.get(i);
            tempStr = "E" + tempStr + "/from " + tempTask.getFrom() + " /to " + tempTask.getTo();
        } else if (currTask instanceof Deadlines) {
            Deadlines tempTask = (Deadlines) tasks.get(i);
            tempStr = "D" + tempStr + "/by " + tempTask.getBy();
        }
        return tempStr;
    }

    public void store(String input) {
        Task task = new Task(input);
        this.tasks.add(task);
        System.out.println("added: " + input);
    }

    //==============================addToDo==================================================
    public void addToDo(String input) {
        this.addToDo(input, true);
    }

    public void addToDo(String input, boolean print) {
        Task toDo = new ToDos(input);
        this.tasks.add(toDo);
        save();

        if (print) {
            System.out.println("Got it. I've added this ToDo:\n" + toDo);
            this.printRemaining();
        }
    }

    //==============================addDeadline==================================================
    public void addDeadline(String input) throws InvalidDeadlineException {
        this.addDeadline(input, true);
    }

    public void addDeadline(String input, boolean print) throws InvalidDeadlineException {
        String[] info = input.split("/by ", 2);
        if (info.length < 2) {
            throw new InvalidDeadlineByException();
        }
        Task deadline = new Deadlines(info[0], info[1]);
        this.tasks.add(deadline);
        save();

        if (print) {
            System.out.println("Got it. I've added this Deadline:\n" + deadline);
            this.printRemaining();
        }
    }

    //==============================addEvent==================================================
    public void addEvent(String input) throws InvalidEventException {
        this.addEvent(input, true);
    }

    public void addEvent(String input, boolean print) throws InvalidEventException {
        String[] info = input.split("/from ", 2);
        if (info.length < 2) {
            throw new InvalidEventFromException();
        }

        String[] info2 = info[1].split(" /to ", 2);
        if (info2.length < 2) {
            throw new InvalidEventToException();
        }

        Task event = new Events(info[0], info2[0], info2[1]);
        this.tasks.add(event);
        save();

        if (print) {
            System.out.println("Got it. I've added this Event:\n" + event);
            this.printRemaining();
        }
    }
}
