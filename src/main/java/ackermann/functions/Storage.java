package ackermann.functions;

import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidCodeException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineByException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventFromException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventToException;
import ackermann.task.Deadlines;
import ackermann.task.Events;
import ackermann.task.Task;
import ackermann.task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to interact with saved tasks
 */
public class Storage {
    public final Path FILEPATH ;

    /**
     * Constructor for Storage
     * @param filepath
     */
    public Storage(Path filepath) {
        this.FILEPATH = filepath;
    }

    /**
     * Returns a list of tasks based on input file
     * @return A list of tasks based on input file
     * @throws CheckedException
     */
    public List<Task> load() throws CheckedException {
        try {
            Scanner fileIn = new Scanner(new File(String.valueOf(this.FILEPATH)));
            assert fileIn != null : "File not found";
            List<Task> tasks = new ArrayList<>();

            while (fileIn.hasNext()) {
                String next = fileIn.nextLine();
                //scans next line
                String[] taskStr = next.split(" \\| ");
                String type = taskStr[0];
                boolean status = taskStr[1].equals("1");
                String value = taskStr[2];
                assert !type.isEmpty() : "type of task is empty";
                assert !value.isEmpty() : "value of task is empty";

                switch (type) {
                case "T":
                    addToDo(tasks, value, status);
                    break;
                case "D":
                    addDeadline(tasks, value, status);
                    break;
                case "E":
                    addEvent(tasks, value, status);
                    break;
                default:
                    throw new InvalidCodeException();
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            File newFile = new File(String.valueOf(this.FILEPATH));
            try {
                boolean success = newFile.createNewFile();
            } catch (IOException ex) {
                System.out.println("Error creating new file!");
            }
            return new ArrayList<Task>();
        }
    }

    /**
     * Adds toDo tasks to list of tasks for constructor
     *
     * @param tasks List of tasks to add result to.
     * @param value Name of the task.
     * @param status Checks if the task is completed.
     */
    private void addToDo(List<Task> tasks, String value, boolean status) {
        Task tempToDo = new ToDos(value);
        if (status) {
            tempToDo.markAsDone();
        }
        tasks.add(tempToDo);
    }

    /**
     * Adds deadline tasks to list of tasks for constructor
     *
     * @param tasks List of tasks to add result to.
     * @param value Name of the task, and deadline to be completed by.
     *              Takes on the form "something /by something".
     * @param status Checks if the task is completed.
     */
    private void addDeadline(List<Task> tasks, String value, boolean status) throws InvalidDeadlineByException {
        String[] deadlineInfo = value.split("/by ", 2);
        if (deadlineInfo.length < 2) {
            throw new InvalidDeadlineByException();
        }

        try {
            String name = deadlineInfo[0];
            LocalDate by = LocalDate.parse(deadlineInfo[1]);
            Task tempDeadline = new Deadlines(name, by);
            if (status) {
                tempDeadline.markAsDone();
            }
            tasks.add(tempDeadline);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date!\nFollow the format 'YYYY-MM-DD'.");
        }
    }

    /**
     * Adds event tasks to list of tasks for constructor
     *
     * @param tasks List of tasks to add result to.
     * @param value Name of the task, from when and to when.
     *              Takes on the form "something /from something /to something".
     * @param status Checks if the task is completed.
     */
    private void addEvent(List<Task> tasks, String value, boolean status) throws InvalidEventException {
        String[] eventInfo1 = value.split("/from ", 2);
        if (eventInfo1.length < 2) {
            throw new InvalidEventFromException();
        }

        String[] eventInfo2 = eventInfo1[1].split(" /to ", 2);
        if (eventInfo2.length < 2) {
            throw new InvalidEventToException();
        }

        try {
            LocalDate from = LocalDate.parse(eventInfo2[0]);
            LocalDate to = LocalDate.parse(eventInfo2[1]);
            Task tempEvent = new Events(eventInfo1[0], from, to);
            if (status) {
                tempEvent.markAsDone();
            }
            tasks.add(tempEvent);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date!\nFollow the format 'YYYY-MM-DD'.");
        }
    }


    /**
     * Saves tasks to a file
     * @param tasks information to save to the file
     */
    public void save(TaskList tasks) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            String tempStr = getString(tasks.get(i));

            if (i == tasks.size() - 1) {
                str.append(tempStr);
            } else {
                str.append(tempStr).append("\n");
            }
        }
        try {
            FileWriter myWriter = new FileWriter(String.valueOf(this.FILEPATH));
            myWriter.write(String.valueOf(str));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error Saving New Task");
        }
    }

    /**
     * Used for save method to get the string to save to file
     * @param currTask task to stringify
     * @return stringified task
     */
    private String getString(Task currTask) {
        boolean status = currTask.getStatus().equals("X");
        String tempStr = "";

        if (status) {
            tempStr += " | 1 | " + currTask.getName();
        } else {
            tempStr += " | 0 | " + currTask.getName();
        }

        if (currTask instanceof ToDos) {
            tempStr = "T" + tempStr;
        } else if (currTask instanceof Events tempTask) {
            tempStr = "E" + tempStr + "/from " + tempTask.getSaveFrom() + " /to " + tempTask.getSaveTo();
        } else if (currTask instanceof Deadlines tempTask) {
            tempStr = "D" + tempStr + "/by " + tempTask.getSaveBy();
        }
        return tempStr;
    }
}
