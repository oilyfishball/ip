package ackermann.ui;

import ackermann.functions.TaskList;
import ackermann.functions.Ui;
import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidTargetException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineByException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventFromException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventToException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UiTest {

    @Test
    void testValidInput() throws CheckedException {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();

        ui.addToDo(tasks, "Something");
        assertEquals(1, tasks.size());
        ui.addDeadline(tasks, "Nothing /by 2025-09-01");
        assertEquals(2, tasks.size());
        ui.addDeadline(tasks, "Submit report /by 2025-09-01");
        assertEquals(3, tasks.size());
    }

    @Test
    void invalid_Target() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();

        assertThrows(InvalidTargetException.class, () -> ui.delete(tasks, 1));
        assertThrows(InvalidTargetException.class, () -> ui.mark(tasks, 1));
        assertThrows(InvalidTargetException.class, () -> ui.unmark(tasks, 1));
    }

    @Test
    void invalid_Deadline() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();

        assertThrows(InvalidDeadlineByException.class, () -> ui.addDeadline(tasks, "something"));
        assertThrows(InvalidDeadlineByException.class, () -> ui.addDeadline(tasks, "something /by"));
    }

    @Test
    void invalid_Event() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();

        assertThrows(InvalidEventFromException.class, () -> ui.addEvent(tasks, "something"));
        assertThrows(InvalidEventFromException.class, () -> ui.addEvent(tasks, "something /from"));
        assertThrows(InvalidEventToException.class, () -> ui.addEvent(tasks, "something /from /to"));
    }
}
