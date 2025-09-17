package ackermann.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to create Deadline object
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for Deadline Task
     * @param name Name of task
     * @param by Deadline of task
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + this.getBy() + ") " + super.getTags();
    }

    /**
     * Gets a string for printing
     * @return A string to output
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Gets a string for saving
     * @return A string for saving
     */
    public String getSaveBy() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
