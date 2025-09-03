package ackermann;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import ackermann.exceptions.CheckedException;
import ackermann.functions.Parser;
import ackermann.functions.Storage;
import ackermann.functions.TaskList;
import ackermann.functions.Ui;

/**
 * ackermann.Main program logic for chatbot
 */
public class Ackermann {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for chatbot
     * @param filePath filepath of saved tasks
     */
    public Ackermann(Path filePath) {
        this.storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            this.ui = new Ui(this.tasks);
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
        boolean isRun = true;

        this.ui.startup();

        while (isRun) {
            try {
                String input = scanner.nextLine();
                ui.printline();
                isRun = ui.isRun(input);
                this.storage.save(tasks);
                if (isRun) {
                    ui.printline();
                }
            } catch (CheckedException e) {
                System.out.println(e.getMessage());
                ui.printline();
            }
        }
        ui.end();
    }

    public static void main(String[] args) {
        new Ackermann(Paths.get(".", "tasks.txt")).run();
    }
}
