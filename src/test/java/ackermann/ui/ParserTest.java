package ackermann.ui;

import ackermann.functions.Parser;
import ackermann.functions.TaskList;
import ackermann.functions.Ui;
import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidCodeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void valid_Inputs() throws CheckedException {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Parser parser = new Parser(ui, tasks);

        assertTrue(parser.parse("todo something"));
        assertTrue(parser.parse("deadline test1 /by 2025-08-23"));
        assertTrue(parser.parse("event test2 /from 2025-08-23 /to 2025-08-24"));
        assertTrue(parser.parse("list"));
        assertTrue(parser.parse("mark 1"));
        assertTrue(parser.parse("unmark 1"));
        assertTrue(parser.parse("delete 1"));
        assertFalse(parser.parse("bye"));
    }

    @Test
    public void invalid_Codeword() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Parser parser = new Parser(ui, tasks);

        assertThrows(InvalidCodeException.class, () -> parser.parse("oops"));
    }


}
