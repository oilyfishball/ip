package ackermann.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for Event Tasks
     * @param name name of task
     * @param from start date
     * @param to end date
     */
    public Events(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }

    /**
     * Gets start date for output
     * @return start date for output
     */
    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Gets start date for saving to txt file
     * @return start date for saving to txt file
     */
    public String getSaveFrom() {
        return from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gets end date for output
     * @return emd date for output
     */
    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Gets end date for saving to txt file
     * @return end date for saving to txt file
     */
    public String getSaveTo() {
        return to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
