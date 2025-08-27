package ackermann.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDate from;
    private LocalDate to;

    public Events(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }

    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getSaveFrom() {
        return from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getSaveTo() {
        return to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
