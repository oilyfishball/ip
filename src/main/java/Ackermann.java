import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import exceptions.CheckedException;
import mypackage.*;

public class Ackermann {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

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

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(this.ui, this.tasks);
        boolean run = true;
        Startup.print_startup();

        while (run) {
            try {
                String input = scanner.nextLine();
                Global.printline();
                run = parser.parse(input);
                this.storage.save(tasks);
                if (run) Global.printline();
            } catch (CheckedException e) {
                System.out.println(e.getMessage());
                Global.printline();
            }
        }
        Startup.print_end();
    }

    public static void main(String[] args) {
        new Ackermann(Paths.get(".", "tasks.txt")).run();
    }
}
