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

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Ui {
    public void list(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            int curr = i + 1;

            System.out.println(curr + ". " + tasks.get(i));
        }
    }

    public void showLoadingError() {
        System.out.println("Error loading saved info!");
    }

    public void mark(TaskList tasks, int i) throws InvalidTargetException {
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

    public void unmark(TaskList tasks, int i) throws InvalidTargetException {
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

    public void delete(TaskList tasks, int i) throws InvalidTargetException {
        try {
            int id = i - 1;
            Task currTask = tasks.get(id);
            tasks.remove(id);
            System.out.println("Noted, I've removed this task:");
            System.out.println(currTask.toString());
            this.printRemaining(tasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTargetException();
        }
    }

/*
    public void store(String input) {
        Task task = new Task(input);
        this.tasks.add(task);
        System.out.println("added: " + input);
    }

 */

    public void printRemaining(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    //==============================addToDo==================================================
    public void addToDo(TaskList tasks, String input) {
        Task toDo = new ToDos(input);
        tasks.add(toDo);

        System.out.println("Got it. I've added this ToDo:\n" + toDo);
        this.printRemaining(tasks);
    }

    //==============================addDeadline==================================================
    public void addDeadline(TaskList tasks, String input) throws InvalidDeadlineException {
        String[] info = input.split("/by ", 2);
        if (info.length < 2) {
            throw new InvalidDeadlineByException();
        }
        try {
            LocalDate date = LocalDate.parse(info[1]);
            Task deadline = new Deadlines(info[0], date);
            tasks.add(deadline);

            System.out.println("Got it. I've added this Deadline:\n" + deadline);
            this.printRemaining(tasks);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date!\nFollow the format 'YYYY-MM-DD'.");
        }
    }

    //==============================addEvent==================================================
    public void addEvent(TaskList tasks, String input) throws InvalidEventException {
        String[] info = input.split("/from ", 2);
        if (info.length < 2) {
            throw new InvalidEventFromException();
        }

        String[] info2 = info[1].split(" /to ", 2);
        if (info2.length < 2) {
            throw new InvalidEventToException();
        }
        try {
            LocalDate from = LocalDate.parse(info2[0]);
            LocalDate to = LocalDate.parse(info2[1]);
            Task event = new Events(info[0], from, to);
            tasks.add(event);

            System.out.println("Got it. I've added this Event:\n" + event);
            this.printRemaining(tasks);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date!\nFollow the format 'YYYY-MM-DD'.");
        }
    }
}
