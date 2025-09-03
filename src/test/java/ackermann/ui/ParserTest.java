package ackermann.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ackermann.functions.Parser;
import ackermann.functions.TaskList;
import ackermann.functions.Ui;
import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidCodeException;
import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void valid_Inputs() throws CheckedException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui(tasks);
        Parser parser = new Parser(tasks);

        String toDo = """
                Got it. I've added this ToDo:
                [T][ ] something
                Now you have 1 tasks in the list.""";
        assertEquals(toDo, parser.parse("todo something"));

        String deadline = """
                Got it. I've added this Deadline:
                [D][ ] test1 (by Aug 23 2025)
                Now you have 2 tasks in the list.""";
        assertEquals(deadline, parser.parse("deadline test1 /by 2025-08-23"));

        String event = """
                Got it. I've added this Event:
                [E][ ] project meeting (from: Dec 1 2019 to: Dec 15 2019)
                Now you have 3 tasks in the list.""";
        assertEquals(event, parser.parse("event project meeting /from 2019-12-01 /to 2019-12-15"));

        String list = """
                Here are the tasks in your list:
                1. [T][ ] something
                2. [D][ ] test1 (by Aug 23 2025)
                3. [E][ ] project meeting (from: Dec 1 2019 to: Dec 15 2019)""";
        assertEquals(list, parser.parse("list"));

        String mark = """
                Nice! I've marked this task as done:
                [T][X] something""";
        assertEquals(mark, parser.parse("mark 1"));

        String unmark = """
                Ok, I've marked this task as not done yet:
                [T][ ] something""";
        assertEquals(unmark, parser.parse("unmark 1"));

        String delete = """
                Noted, I've removed this task:
                [T][ ] something
                Now you have 2 tasks in the list.""";
        assertEquals(delete, parser.parse("delete 1"));

        String bye = "Bye. Hope to see you again soon!";
        assertEquals(bye, parser.parse("bye"));
    }

    @Test
    public void invalid_Codeword() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui(tasks);
        Parser parser = new Parser(tasks);

        assertThrows(InvalidCodeException.class, () -> parser.parse("oops"));
    }


}
