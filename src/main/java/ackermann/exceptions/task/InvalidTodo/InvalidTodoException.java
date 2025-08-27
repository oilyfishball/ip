package ackermann.exceptions.task.InvalidTodo;

import ackermann.exceptions.task.InvalidTaskException;

public class InvalidTodoException extends InvalidTaskException {
    public InvalidTodoException() {
        super("Description of todo cannot be empty!");
    }
}
