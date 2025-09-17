package ackermann.codewords;

import ackermann.exceptions.CheckedException;
import ackermann.functions.TaskList;

/**
 * Handles Tag Codeword
 */
public class TagCodeword extends Codeword {
    private TaskList tasks;
    private String[] newWords;

    /**
     * Constructor for TagCodeword
     * @param tasks Task list that contain task to mark
     * @param newWords id and tag name
     */
    public TagCodeword(TaskList tasks, String[] newWords) {
        this.tasks = tasks;
        this.newWords = newWords;
    }

    @Override
    public String execute() throws CheckedException {
        int idx = Integer.parseInt(newWords[0]);
        String tag = newWords[1];
        String output = "Noted bro, I have successfully tagged:\n";
        this.tasks.tag(idx - 1, tag);
        return output + this.tasks.get(idx - 1);
    }
}
