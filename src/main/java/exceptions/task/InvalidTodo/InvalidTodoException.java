package exceptions.task.InvalidTodo;

import exceptions.task.InvalidTaskException;

public class InvalidTodoException extends InvalidTaskException {
    public InvalidTodoException() {
        super("Description of todo cannot be empty!");
    }
}
