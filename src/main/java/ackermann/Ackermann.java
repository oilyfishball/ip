package ackermann;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import ackermann.exceptions.CheckedException;

/**
 * Main program logic for chatbot
 */
public class Ackermann {
    private static final String LINE = "-----------------------------------------------------";
    private static final String STARTUP = "Hello! I'm Ackermann\nWhat can I do for you?";
    private static final String END = "Bye. Hope to see you again soon!";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for chatbot
     * @param filePath filepath of saved tasks
     */
    public Ackermann(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (CheckedException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(this.ui, this.tasks);
        boolean isRun = true;

        System.out.println(LINE);
        System.out.println(STARTUP);
        System.out.println(LINE);

        while (isRun) {
            try {
                String input = scanner.nextLine();
                System.out.println(LINE);
                isRun = parser.parse(input);
                this.storage.save(tasks);
                if (isRun) System.out.println(LINE);
            } catch (CheckedException e) {
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        }

        System.out.println(END);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        new Ackermann(Paths.get(".", "tasks.txt")).run();
    }
}
