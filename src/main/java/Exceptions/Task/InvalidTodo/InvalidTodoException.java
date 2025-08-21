package Exceptions.Task.InvalidTodo;

import Exceptions.Task.InvalidTaskException;

public class InvalidTodoException extends InvalidTaskException {
    public InvalidTodoException() {
        super("Description of todo cannot be empty!");
    }
}
